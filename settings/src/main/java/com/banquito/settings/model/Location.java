package com.banquito.settings.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Builder;
import org.springframework.data.annotation.Version;

@Data
@Builder
@Document(collection = "locations")
public class Location {
	@Id
	private String id;
	private String countryName;
	private String countryPhoneCode;
	private List<Province> provinces;

	@Version
	private Integer version;
}
