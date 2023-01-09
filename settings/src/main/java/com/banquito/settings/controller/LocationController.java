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

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findAll() {
		return ResponseEntity.ok(this.locationService.findAll());
	}

	@RequestMapping(value = "/country/{country}/province/{province}/canton/{canton}/parish/{parish}", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findByCompleteLocation(
			@PathVariable("country") String country,
			@PathVariable("province") String province,
			@PathVariable("canton") String canton,
			@PathVariable("parish") String parish) {
		return ResponseEntity
				.ok(this.locationService.findByCompleteLocation(country, province, canton, parish));
	}

	@RequestMapping(value = "/country/{country}", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findByCountry(@PathVariable("country") String country) {
		return ResponseEntity.ok(this.locationService.findByCountry(country));
	}

	@RequestMapping(value = "/province/{province}", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findByProvince(@PathVariable("province") String province) {
		return ResponseEntity.ok(this.locationService.findByProvince(province));
	}

	@RequestMapping(value = "/canton/{canton}", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findByCanton(@PathVariable("canton") String canton) {
		return ResponseEntity.ok(this.locationService.findByCanton(canton));
	}

	@RequestMapping(value = "/parish/{parish}", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findByParish(@PathVariable("parish") String parish) {
		return ResponseEntity.ok(this.locationService.findByParish(parish));
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> createLocation(@RequestBody Location location) {
		return ResponseEntity.ok("Location created successfully");
	}

	@RequestMapping(value = "/code/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateLocation(@PathVariable("code") String code, @RequestBody Location location) {
		this.locationService.updateLocation(code, location);
		return ResponseEntity.ok("Location updated successfully");
	}

	@RequestMapping(value = "/code/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLocation(@PathVariable("code") String code, @RequestBody Location location) {
		this.locationService.deleteLocation(code, location);
		return ResponseEntity.ok("Location deleted successfully");
	}
}
