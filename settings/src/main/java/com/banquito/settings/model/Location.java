package com.banquito.settings.model;

import lombok.Data;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "locations")
public class Location {
	@Id
	private String id;
	private String provincia;
	private String parroquia;
	@Indexed
	private String canton;
	private String codigoPostal;
	@Version
	private Long version;
}