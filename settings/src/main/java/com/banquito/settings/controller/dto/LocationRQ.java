package com.banquito.settings.controller.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRQ implements Serializable {

	private List<com.banquito.settings.model.Provincia> provincias;

}
