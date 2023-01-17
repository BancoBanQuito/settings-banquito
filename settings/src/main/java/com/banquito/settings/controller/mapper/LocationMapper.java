package com.banquito.settings.controller.mapper;

import com.banquito.settings.controller.dto.LocationRQ;
import com.banquito.settings.controller.dto.LocationRS;
import com.banquito.settings.model.Location;

public class LocationMapper {

	public static Location toLocation(LocationRQ rq) {
		return Location.builder()
				.provinces(rq.getProvinces()).build();
	}

	public static LocationRS toLocationRS(Location location) {
		return LocationRS.builder()
				.countryName(location.getCountryName())
				.countryPhoneCode(location.getCountryPhoneCode())
				.provinces(location.getProvinces())
				.build();
	}
}
