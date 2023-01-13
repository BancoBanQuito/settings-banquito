package com.banquito.settings.controller.mapper;

import com.banquito.settings.controller.dto.BranchRQ;
import com.banquito.settings.controller.dto.BranchRS;
import com.banquito.settings.model.Branch;

public class BranchMapper {

    public static Branch toBranchRQ(BranchRQ rq) {
        return Branch.builder()
                .name(rq.getName())
                .phoneNumber(rq.getPhoneNumber())
                .address(rq.getAddress())
                .branchOfficeHours(rq.getBranchOfficeHours()).build();
    }

    public static BranchRS toBranchRS(Branch branch) {
        return BranchRS.builder()
                .name(branch.getName())
                .phoneNumber(branch.getPhoneNumber())
                .address(branch.getAddress())
                .branchOfficeHours(branch.getBranchOfficeHours()).build();
    }
}
