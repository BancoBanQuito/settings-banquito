package com.banquito.settings.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.Branch;
import com.banquito.settings.repository.BankEntityRepository;
import com.banquito.settings.repository.BranchRepository;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final BankEntityRepository bankEntityRepository;

    public BranchService(BranchRepository branchRepository, BankEntityRepository bankEntityRepository) {
        this.branchRepository = branchRepository;
        this.bankEntityRepository = bankEntityRepository;
    }

    public List<Branch> findAllByOrderByLocation() {
        return branchRepository.findAllByOrderByLocation();
    }

    public List<Branch> findByNameLike(String name) {
        return branchRepository.findByNameLike(name);
    }

    public Branch findByName(String name) {
        return branchRepository.findByName(name);
    }

    @Transactional
    public void createBranch(Branch branch) {
        Boolean branchNameExists = this.branchRepository.existsByName(branch.getName());
        Boolean branchAddressExists = this.branchRepository.existsByAddress(branch.getAddress());
        if (branchNameExists || branchAddressExists)
            throw new RuntimeException("There is already a branch named like this or in that location");
        else
            branch.setMondayToFriday(
                timeToString(
                    branch.getBranchOfficeHours().getOpeningTimeMondayFriday(),
                    branch.getBranchOfficeHours().getClosingTimeMondayFriday()
                )
            );
            if (
                branch.getBranchOfficeHours().getOpeningTimeSaturday() == null 
                || branch.getBranchOfficeHours().getClosingTimeSaturday() == null
            )
                branch.setSaturday("Cerrado");
            else
                branch.setSaturday(
                    timeToString(
                        branch.getBranchOfficeHours().getOpeningTimeSaturday(),
                        branch.getBranchOfficeHours().getClosingTimeSaturday()
                    )
                );
            branch.setBankEntity(bankEntityRepository.findAll().iterator().next());
            checkBranchHasNameAgency(branch);
    }

    @Transactional
    public void updateBranch(String name, Branch branch) {
        Boolean branchExists = this.branchRepository.existsByName(name);
        if (branchExists) {
            Branch branchToUpdate = this.branchRepository.findByName(name);
            branchToUpdate.setName(branch.getName());
            branchToUpdate.setPhoneNumber(branch.getPhoneNumber());
            branchToUpdate.setAddress(branch.getAddress());
            branchToUpdate.setBranchOfficeHours(branch.getBranchOfficeHours());
            branchToUpdate.setMondayToFriday(
                timeToString(
                    branch.getBranchOfficeHours().getOpeningTimeMondayFriday(),
                    branch.getBranchOfficeHours().getClosingTimeMondayFriday()
                )
            );
            branchToUpdate.setSaturday(
                timeToString(
                    branch.getBranchOfficeHours().getOpeningTimeSaturday(),
                    branch.getBranchOfficeHours().getClosingTimeSaturday()
                )
            );
            branchToUpdate.setBankEntity(bankEntityRepository.findAll().iterator().next());
            branchToUpdate.setLocation(branch.getLocation());
            checkBranchHasNameAgency(branchToUpdate);
        }
        else {
            throw new RuntimeException("The branch was not found");
        }
    }

    @Transactional
    public void deleteBranch(String name) {
        Boolean branchExists = this.branchRepository.existsByName(name);
        if (branchExists)
            this.branchRepository.deleteByName(name);
        else
            throw new RuntimeException("The branch was not found");
    }

    private void checkBranchHasNameAgency(Branch branch) {
        if (branch.getName().toLowerCase().contains("agencia"))
            this.branchRepository.save(branch);
        else
            branch.setName("Agencia " + branch.getName());
            this.branchRepository.save(branch);
    }

    private String timeToString(LocalTime timeOpening, LocalTime timeClosing) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTimeOpening = timeOpening.format(formatter);
        String formattedTimeClosing = timeClosing.format(formatter);
        return formattedTimeOpening + "-" + formattedTimeClosing;
    }
}
