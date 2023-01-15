package com.banquito.settings.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.banquito.settings.model.Location.Canton;
import com.banquito.settings.model.Location.Parroquia;
import com.banquito.settings.model.Location.Provincia;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRQ implements Serializable {

	private List<Provincia> provincias;

}
