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

    public List<Branch> findAll(){
        return branchRepository.findAll();
    }

    public List<Branch> findByNameLike(String name){
        return branchRepository.findByNameLike(name);
    }

    @Transactional
    public void createBranch(Branch branch){
        this.branchRepository.save(branch);
    }

    @Transactional
    public void updateBranch(String code, Branch branch){
        this.branchRepository.save(branch);
    }

    @Transactional
    public void deleteBranch(String code, Branch branch){
        this.branchRepository.delete(branch);
    }
    
}
