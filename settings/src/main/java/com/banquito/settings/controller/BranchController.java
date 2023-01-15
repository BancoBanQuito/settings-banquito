package com.banquito.settings.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.controller.dto.BranchRQ;
import com.banquito.settings.controller.dto.BranchRS;
import com.banquito.settings.controller.mapper.BranchMapper;
import com.banquito.settings.model.Branch;
import com.banquito.settings.service.BranchService;

@RestController
@RequestMapping("/api/branch")
public class BranchController {
    
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object findAll() {
        Iterable<Branch> branches = this.branchService.findAll();
        List<BranchRS> branchesRS = new ArrayList<>();
        for (Branch branch : branches)
            branchesRS.add(BranchMapper.toBranchRS(branch));
        if (branchesRS.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(branchesRS);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Object findByNameLike(@PathVariable("name") String name) {
        Iterable<Branch> branches = this.branchService.findByNameLike(name);
        List<BranchRS> branchesRS = new ArrayList<>();
        for (Branch branch : branches)
            branchesRS.add(BranchMapper.toBranchRS(branch));
        if (branchesRS.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(branchesRS);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object createBranch(@RequestBody BranchRQ branchRQ) {
        try {
            this.branchService.createBranch(BranchMapper.toBranchRQ(branchRQ));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.PUT)
    public Object updateBranch(@PathVariable("id") String id, @RequestBody BranchRQ branchRQ) {
        try {
            this.branchService.updateBranch(id, BranchMapper.toBranchRQ(branchRQ));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public Object deleteBranch(@PathVariable("id") String id) {
        try {
            this.branchService.deleteBranch(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
