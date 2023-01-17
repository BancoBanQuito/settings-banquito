package com.banquito.settings.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parroquia {
	private String nombreParroquia;
	private String codigoPostal;
}
