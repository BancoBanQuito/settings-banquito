package com.banquito.settings.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.banquito.settings.service.LocationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/branch")
public class BranchController {
    
    private final BranchService branchService;
    private final LocationService locationService;

    public BranchController(BranchService branchService, LocationService locationService) {
        this.branchService = branchService;
        this.locationService = locationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object findAllOrderByLocation() {
        List<Branch> branches = this.branchService.findAllByOrderByLocation();
        List<BranchRS> branchesRS = new ArrayList<>();
        for (Branch branch : branches)
            branchesRS.add(BranchMapper.toBranchRS(branch));
        if (branchesRS.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(branchesRS);
    }

    @RequestMapping(value = "/name/like/{name}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Object findByName(@PathVariable("name") String name) {
        Branch branch = this.branchService.findByName(name);
        return ResponseEntity.ok(BranchMapper.toBranchRS(branch));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object createBranch(@RequestBody BranchRQ branchRQ) {
        try {
            List<Object> listName = new ArrayList<>();
            listName.addAll(branchRQ.getLocation().values());
            Map<String, Object> location = this.locationService.getProvinceCantonParish(
                listName.get(0).toString(), listName.get(1).toString(), listName.get(2).toString()
            );
            if (location.isEmpty())
                return ResponseEntity.notFound().build();
            else
                this.branchService.createBranch(BranchMapper.toBranch(branchRQ));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.PUT)
    public Object updateBranch(@PathVariable("name") String name, @RequestBody BranchRQ branchRQ) {
        try {
            List<Object> listName = new ArrayList<>();
            listName.addAll(branchRQ.getLocation().values());
            Map<String, Object> location = this.locationService.getProvinceCantonParish(
                listName.get(0).toString(), listName.get(1).toString(), listName.get(2).toString()
            );
            if (location.isEmpty())
                return ResponseEntity.notFound().build();
            else
                this.branchService.updateBranch(name, BranchMapper.toBranch(branchRQ));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.DELETE)
    public Object deleteBranch(@PathVariable("name") String name) {
        try {
            this.branchService.deleteBranch(name);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
