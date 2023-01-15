package com.banquito.settings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.settings.model.Location;
import com.banquito.settings.repository.LocationRepository;

@Service
public class LocationService {

	private final LocationRepository locationRepository;

	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	public List<Location> findByProvincia(String provincia) {
		return locationRepository.findByProvincia(provincia);
	}

	public List<Location> findByCanton(String canton) {
		return locationRepository.findByCanton(canton);
	}

	public List<Location> findByParroquia(String parroquia) {
		return locationRepository.findByParroquia(parroquia);
	}

	public Location findById(String id) {
		Optional<Location> locationOpt = locationRepository.findById(id);
		if (locationOpt.isPresent()) {
			return locationOpt.get();
		} else {
			throw new RuntimeException("No se encontro la locacion");
		}
	}

	public Location save(Location location) {
		return locationRepository.save(location);
	}

	public void delete(Location location) {
		locationRepository.delete(location);
	}
}