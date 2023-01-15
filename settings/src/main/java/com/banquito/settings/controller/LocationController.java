package com.banquito.settings.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	/*
	 * @RequestMapping(value = "/provincia", method = RequestMethod.POST)
	 * public ResponseEntity<Location> createProvincia(@RequestBody Location
	 * location) {
	 * return ResponseEntity.ok(this.locationService.save(location));
	 * }
	 */

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findAll() {
		return ResponseEntity.ok(this.locationService.findAll());
	}

}
