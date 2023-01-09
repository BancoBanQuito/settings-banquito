package com.banquito.settings.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.Location;
import com.banquito.settings.repository.LocationRepository;

@Service
public class LocationService {
	private final LocationRepository locationRepository;

	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	public List<Location> findByCompleteLocation(String country, String province, String canton, String parish) {
		return locationRepository.findByCompleteLocation(country, province, canton, parish);
	}

	public List<Location> findByCountry(String country) {
		return locationRepository.findByCountry(country);
	}

	public List<Location> findByProvince(String province) {
		return locationRepository.findByProvince(province);
	}

	public List<Location> findByCanton(String canton) {
		return locationRepository.findByCanton(canton);
	}

	public List<Location> findByParish(String parish) {
		return locationRepository.findByParish(parish);
	}

	@Transactional
	public void createLocation(Location location) {
		this.locationRepository.save(location);
	}

	@Transactional
	public void updateLocation(String code, Location location) {
		this.locationRepository.save(location);
	}

	@Transactional
	public void deleteLocation(String code, Location location) {
		this.locationRepository.delete(location);
	}
}