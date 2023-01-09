package com.banquito.settings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.model.Branch;
import com.banquito.settings.service.BranchService;

@RestController
@RequestMapping("/api/branch")
public class BranchController {
    
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Object findAll() {
        return ResponseEntity.ok(this.branchService.findAll());
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Object findByNameLike(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.branchService.findByNameLike(name));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object createBranch(@RequestBody Branch branch) {
        return ResponseEntity.ok("Branch created successfully");
    }

    @RequestMapping(value = "/code/{code}", method = RequestMethod.PUT)
    public Object createBranch(@PathVariable("code") String code, @RequestBody Branch branch) {
        return ResponseEntity.ok("Branch updated successfully");
    }

    @RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE)
    public Object deleteBranch(@PathVariable("code") String code, @RequestBody Branch branch) {
        return ResponseEntity.ok("Branch deleted successfully");
    }
}
