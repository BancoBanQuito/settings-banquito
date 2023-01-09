package com.banquito.settings.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "branch")
public class Branch {

    @Id
    private String code;

    @Field(value = "international_code")
    private String internationalCode;

    @Field(value = "bank_entity_code")
    private String bankEntityCode;

    @Field(value = "international_bank_code")
    private String internationalBankCode;

    @Field(value = "location_code")
    private Integer locationCode;

    @Field(value = "name")
    private String name;

    @Field(value = "address")
    private String address;

}
