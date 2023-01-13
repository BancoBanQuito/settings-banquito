package com.banquito.settings.model;

import java.util.List;

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

    private BranchOfficeHour branchOfficeHours;
    private BankEntity bankEntity;
    private Location location;
    private List<Holiday> holidays;

    @Version
    private Integer version;

}
