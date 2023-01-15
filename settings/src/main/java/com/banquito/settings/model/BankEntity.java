package com.banquito.settings.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "bank_entities")
public class BankEntity {
	
	@Id
	private String code;

	private String internationalBankCode;
	private String name;

	private List<Branch> branches;

	@Version
	private Long version;
}
