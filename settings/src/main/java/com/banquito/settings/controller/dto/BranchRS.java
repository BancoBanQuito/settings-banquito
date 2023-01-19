package com.banquito.settings.controller.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchRS implements Serializable {

    private String name;
    private Integer phoneNumber;
    private String address;
    private String mondayToFriday;
    private String saturday;
    private Map<String, Object> location;

}
