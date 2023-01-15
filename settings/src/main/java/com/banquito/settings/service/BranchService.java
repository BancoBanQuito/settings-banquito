package com.banquito.settings.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.Branch;
import com.banquito.settings.repository.BranchRepository;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Iterable<Branch> findAll() {
        return branchRepository.findAll();
    }

    public List<Branch> findByNameLike(String name) {
        return branchRepository.findByNameLike(name);
    }

    @Transactional
    public void createBranch(Branch branch) {
        Boolean branchNameExists = this.branchRepository.existsByName(branch.getName());
        Boolean branchAddressExists = this.branchRepository.existsByAddress(branch.getAddress());
        if (branchNameExists || branchAddressExists)
            throw new RuntimeException("There is already a branch named like this or in that location");
        else
            checkBranchHasNameAgency(branch);
    }

    @Transactional
    public void updateBranch(String id, Branch branch) {
        Boolean branchExists = this.branchRepository.existsById(id);
        if (branchExists) {
            Branch branchToUpdate = this.branchRepository.findById(id).get();
            branchToUpdate.setName(branch.getName());
            branchToUpdate.setPhoneNumber(branch.getPhoneNumber());
            branchToUpdate.setAddress(branch.getAddress());
            branchToUpdate.setBranchOfficeHours(branch.getBranchOfficeHours());
            checkBranchHasNameAgency(branchToUpdate);
        }
        else {
            throw new RuntimeException("The branch was not found");
        }
    }

    @Transactional
    public void deleteBranch(String id) {
        Boolean branchExists = this.branchRepository.existsById(id);
        if (branchExists)
            this.branchRepository.deleteById(id);
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
}
