package com.banquito.settings.controller.mapper;

import com.banquito.settings.controller.dto.BranchRQ;
import com.banquito.settings.controller.dto.BranchRS;
import com.banquito.settings.model.Branch;

public class BranchMapper {

    public static Branch toBranch(BranchRQ rq) {
        return Branch.builder()
                .name(rq.getName())
                .phoneNumber(rq.getPhoneNumber())
                .address(rq.getAddress())
                .branchOfficeHours(rq.getBranchOfficeHours())
                .location(rq.getLocation()).build();
    }

    public static BranchRS toBranchRS(Branch branch) {
        return BranchRS.builder()
                .name(branch.getName())
                .phoneNumber(branch.getPhoneNumber())
                .address(branch.getAddress())
                .mondayToFriday(branch.getMondayToFriday())
                .saturday(branch.getSaturday())
                .location(branch.getLocation()).build();
    }
}
