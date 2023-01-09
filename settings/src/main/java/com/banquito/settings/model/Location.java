package com.banquito.settings.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "location")
public class Location {
	@Id
	private String code;

	@Field(value = "provincia")
	private String province;

	@Field(value = "parroquia")
	private String parish;

	@Field(value = "canton")
	private String canton;

	@Field(value = "country")
	private String country; // "Ecuador"

	@Field(value = "postal_code")
	private String postalCode;
}
