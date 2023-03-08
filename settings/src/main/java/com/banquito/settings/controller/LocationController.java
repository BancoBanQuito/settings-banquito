package com.banquito.settings.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.controller.dto.LocationRS;
import com.banquito.settings.controller.mapper.LocationMapper;
import com.banquito.settings.model.Canton;
import com.banquito.settings.model.Location;
import com.banquito.settings.model.Parish;
import com.banquito.settings.model.Province;
import com.banquito.settings.service.LocationService;

@RestController
@CrossOrigin(origins = "*", methods = { org.springframework.web.bind.annotation.RequestMethod.GET,
        org.springframework.web.bind.annotation.RequestMethod.POST,
        org.springframework.web.bind.annotation.RequestMethod.PUT,
        org.springframework.web.bind.annotation.RequestMethod.DELETE})
@RequestMapping("/api/location")
public class LocationController {

	private final LocationService locationService;

	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}

	@RequestMapping(value = "/provinces", method = RequestMethod.GET)
	public ResponseEntity<List<Province>> findAllProvinces() {
		return ResponseEntity.ok(this.locationService.findAllProvinces());
	}

	@RequestMapping(value = "/cantons", method = RequestMethod.GET)
	public ResponseEntity<List<Canton>> findAllCantons() {
		return ResponseEntity.ok(this.locationService.findAllCantons());
	}

	@RequestMapping(value = "/parishes", method = RequestMethod.GET)
	public ResponseEntity<List<Parish>> findAllParrishes() {
		return ResponseEntity.ok(this.locationService.findAllParishes());
	}

	@RequestMapping(value = "/province/{provinceName}", method = RequestMethod.GET)
	public ResponseEntity<Province> findByProvince(@PathVariable String provinceName) {
		return ResponseEntity.ok(this.locationService.findProvincesByProvinceName(provinceName));

	}

	@RequestMapping(value = "/canton/{cantonName}", method = RequestMethod.GET)
	public ResponseEntity<Canton> findByCanton(@PathVariable String cantonName) {
		return ResponseEntity.ok(this.locationService.findCantonsByCantonName(cantonName));
	}

	@RequestMapping(value = "/parish/{parishName}", method = RequestMethod.GET)
	public ResponseEntity<Parish> findByParrish(@PathVariable String parishName) {
		return ResponseEntity.ok(this.locationService.findParishesByParishName(parishName));
	}

	@RequestMapping(value = "/province/{provinceName}/canton/{cantonName}/parish/{parishName}", method = RequestMethod.GET)
	public Object getProvinceCantonParish(@PathVariable("provinceName") String provinceName,
			@PathVariable("cantonName") String cantonName, @PathVariable("parishName") String parishName) {
		try {
			return locationService.getProvinceCantonParish(provinceName, cantonName, parishName);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/province", method = RequestMethod.POST)
	public Object createProvince(@RequestBody Map<String, String> requestBody) {
		try {
			Province province = Province.builder()
					.provinceName(requestBody.get("provinceName"))
					.build();
			this.locationService.createProvince("63c424969696e95c3534f89b", province);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/canton", method = RequestMethod.POST)
	public Object createCanton(@RequestBody Map<String, String> requestBody) {
		try {
			String provinceName = requestBody.get("provinceName");
			String cantonName = requestBody.get("cantonName");
			Canton canton = Canton.builder()
					.cantonName(cantonName)
					.parishes(new ArrayList<>())
					.build();
			this.locationService.createCanton(provinceName, canton);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/parish", method = RequestMethod.POST)
	public Object createParish(@RequestBody Map<String, String> requestBody) {
		try {
			String provinceName = requestBody.get("provinceName");
			String cantonName = requestBody.get("cantonName");
			String parishName = requestBody.get("parishName");
			String zipCode = requestBody.get("zipCode");
			Parish parish = Parish.builder()
					.parishName(parishName)
					.zipCode(zipCode)
					.build();
			this.locationService.createParish(provinceName, cantonName, parish);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/province/{provinceName}", method = RequestMethod.PUT)
	public Object updateProvince(@PathVariable("provinceName") String provinceName,
			@RequestBody Map<String, String> requestBody) {
		try {
			this.locationService.updateProvince("63c424969696e95c3534f89b", provinceName,
					requestBody.get("provinceName"));
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/canton/{cantonName}", method = RequestMethod.PUT)
	public Object updateCanton(@PathVariable("cantonName") String cantonName,
			@RequestBody Map<String, String> requestBody) {
		try {
			this.locationService.updateCanton(cantonName, requestBody.get("cantonName"));
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/parish/{parishName}", method = RequestMethod.PUT)
	public Object updateParish(@PathVariable("parishName") String parishName,
			@RequestBody Map<String, String> requestBody) {
		try {
			String name = requestBody.get("parishName");
			String zipCode = requestBody.get("zipCode");
			Parish parish = Parish.builder()
					.parishName(name)
					.zipCode(zipCode)
					.build();
			this.locationService.updateParish("63c424969696e95c3534f89b", parishName, parish);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/province/{provinceName}", method = RequestMethod.DELETE)
	public Object deleteProvince(@PathVariable("provinceName") String provinceName) {
		try {
			this.locationService.deleteProvince("63c424969696e95c3534f89b", provinceName);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/canton/{cantonName}", method = RequestMethod.DELETE)
	public Object deleteCanton(@PathVariable("cantonName") String cantonName) {
		try {
			this.locationService.deleteCanton(cantonName);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/parish/{parishName}", method = RequestMethod.DELETE)
	public Object deleteParish(@PathVariable("parishName") String parishName) {
		try {
			this.locationService.deleteParish("63c424969696e95c3534f89b", parishName);
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
