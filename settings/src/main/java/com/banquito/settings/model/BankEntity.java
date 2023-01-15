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
	private String id;

	private String internationalBankCode;
	private String name;

	@Version
	private Long version;
}
