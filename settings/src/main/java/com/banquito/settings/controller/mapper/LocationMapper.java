package com.banquito.settings.controller.mapper;

import com.banquito.settings.controller.dto.LocationRQ;
import com.banquito.settings.controller.dto.LocationRS;
import com.banquito.settings.model.Location;

public class LocationMapper {

	public static Location toLocation(LocationRQ rq) {
		return Location.builder()
				.provincias(rq.getProvincias()).build();
	}

	public static LocationRS toLocationRS(Location location) {
		return LocationRS.builder()
				.nombrePais(location.getNombrePais())
				.codigoTelefonico(location.getCodigoTelefonico())
				.provincias(location.getProvincias())
				.build();
	}
}
