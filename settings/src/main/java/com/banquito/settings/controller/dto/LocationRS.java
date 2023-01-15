package com.banquito.settings.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.banquito.settings.model.Location.Provincia;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRS implements Serializable {

	private String nombrePais;
	private String codigoTelefonico;
	private List<Provincia> provincias;

}
