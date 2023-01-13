package com.banquito.settings.controller.dto;

import java.io.Serializable;

import com.banquito.settings.model.BranchOfficeHour;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchRS implements Serializable {

    private String name;
    private Integer phoneNumber;
    private String address;

    private BranchOfficeHour branchOfficeHours;

}
