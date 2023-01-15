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
	private String nombrePais;
	private String codigoTelefonico;
	private List<Provincia> provincias;

	@Version
	private Integer version;

	@Data
	@Builder
	public static class Provincia {
		private String nombreProvincia;
		private List<Canton> cantones;
	}

	@Data
	@Builder
	public static class Canton {
		private String nombreCanton;
		private List<Parroquia> parroquias;
	}

	@Data
	@Builder
	public static class Parroquia {
		private String nombreParroquia;
		private String codigoPostal;
	}

}
