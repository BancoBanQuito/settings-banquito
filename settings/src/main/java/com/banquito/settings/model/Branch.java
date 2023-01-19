package com.banquito.settings.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "branches")
public class Branch {

    @Id
    private String id;
    private String name;
    private Integer phoneNumber;
    private String address;
    private String mondayToFriday;
    private String saturday;

    private BranchOfficeHour branchOfficeHours;
    private Map<String, Object> location;
    private BankEntity bankEntity;

    @Version
    private Integer version;

}
