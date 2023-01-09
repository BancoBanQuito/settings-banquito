package com.banquito.settings.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "bankEntity")
public class BankEntity {
	@Id
	private String code;

	@Field(value = "internationalBankCode")
	private String internationalBankCode;

	@Field(value = "name")
	private String name;

}
