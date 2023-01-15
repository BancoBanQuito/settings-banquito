package com.banquito.settings.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.controller.dto.LocationRS;
import com.banquito.settings.controller.mapper.LocationMapper;
import com.banquito.settings.model.Location;
import com.banquito.settings.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {

	private final LocationService locationService;

	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}

	@RequestMapping(value = "/provincias", method = RequestMethod.GET)
	public ResponseEntity<List<Location.Provincia>> findAllProvincias() {
		return ResponseEntity.ok(this.locationService.findAllProvincias());
	}

	@RequestMapping(value = "/cantones", method = RequestMethod.GET)
	public ResponseEntity<List<Location.Canton>> findAllCantones() {
		return ResponseEntity.ok(this.locationService.findAllCantones());
	}

	@RequestMapping(value = "/parroquias", method = RequestMethod.GET)
	public ResponseEntity<List<Location.Parroquia>> findAllParroquias() {
		return ResponseEntity.ok(this.locationService.findAllParroquias());
	}

	@RequestMapping(value = "/provincia/{nombreProvincia}", method = RequestMethod.GET)
	public ResponseEntity<Location.Provincia> findByProvincia(@PathVariable String nombreProvincia) {
		return ResponseEntity.ok(this.locationService.findProvinciasByNombreProvincia(nombreProvincia));

	}

	@RequestMapping(value = "/canton/{nombreCanton}", method = RequestMethod.GET)
	public ResponseEntity<Location.Canton> findByCanton(@PathVariable String nombreCanton) {
		return ResponseEntity.ok(this.locationService.findCantonesByNombreCanton(nombreCanton));
	}

	@RequestMapping(value = "/parroquia/{nombreParroquia}", method = RequestMethod.GET)
	public ResponseEntity<Location.Parroquia> findByParroquia(@PathVariable String nombreParroquia) {
		return ResponseEntity.ok(this.locationService.findParroquiasByNombreParroquia(nombreParroquia));
	}

	@RequestMapping(value = "/provincia", method = RequestMethod.PUT)
	public Object createProvince(@RequestBody Map<String, String> requestBody) {
		try {
			Location.Provincia provincia = Location.Provincia.builder()
					.nombreProvincia(requestBody.get("nombreProvincia"))
					.build();
			this.locationService.createProvincia("63c424969696e95c3534f89b", provincia);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/canton", method = RequestMethod.PUT)
	public Object createCanton(@RequestBody Map<String, String> requestBody) {
		try {
			String provinceName = requestBody.get("nombreProvincia");
			String cantonName = requestBody.get("nombreCanton");
			Location.Canton canton = Location.Canton.builder()
					.nombreCanton(cantonName)
					.build();
			this.locationService.createCanton(provinceName, canton);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/parroquia", method = RequestMethod.PUT)
	public Object createParroquia(@RequestBody Map<String, String> requestBody) {
		try {
			String provinceName = requestBody.get("nombreProvincia");
			String cantonName = requestBody.get("nombreCanton");
			String parroquiaName = requestBody.get("nombreParroquia");
			String codigoPostal = requestBody.get("codigoPostal");
			Location.Parroquia parroquia = Location.Parroquia.builder()
					.nombreParroquia(parroquiaName)
					.codigoPostal(codigoPostal)
					.build();
			this.locationService.createParroquia(provinceName, cantonName, parroquia);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object findAll() {
		Iterable<Location> locations = this.locationService.findAll();
		List<LocationRS> locationsRS = new ArrayList<>();
		for (Location location : locations)
			locationsRS.add(LocationMapper.toLocationRS(location));
		if (locationsRS.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(locationsRS);
	}

}
