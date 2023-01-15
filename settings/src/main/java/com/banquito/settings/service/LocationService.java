package com.banquito.settings.service;

import java.util.ArrayList;
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

	public List<Location> findAll() {
		return (List<Location>) locationRepository.findAll();
	}

	public List<Location.Provincia> findAllProvincias() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Location.Provincia> provincias = new ArrayList<Location.Provincia>();
		for (Location location : locations) {
			for (Location.Provincia provincia : location.getProvincias()) {
				provincias.add(provincia);
			}
		}
		return provincias;
	}

	public List<Location.Canton> findAllCantones() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Location.Canton> cantones = new ArrayList<Location.Canton>();
		for (Location location : locations) {
			for (Location.Provincia provincia : location.getProvincias()) {
				for (Location.Canton canton : provincia.getCantones()) {
					cantones.add(canton);
				}
			}
		}
		return cantones;
	}

	public List<Location.Parroquia> findAllParroquias() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Location.Parroquia> parroquias = new ArrayList<Location.Parroquia>();
		for (Location location : locations) {
			for (Location.Provincia provincia : location.getProvincias()) {
				for (Location.Canton canton : provincia.getCantones()) {
					for (Location.Parroquia parroquia : canton.getParroquias()) {
						parroquias.add(parroquia);
					}
				}
			}
		}
		return parroquias;
	}

	public Location.Provincia findProvinciasByNombreProvincia(String nombreProvincia) {
		Location location = locationRepository.findByProvinciasNombreProvincia(nombreProvincia);
		List<Location.Provincia> provincias = location.getProvincias();
		Location.Provincia provinciaMatched = null;
		for (Location.Provincia provincia : provincias) {
			if (provincia.getNombreProvincia().equals(nombreProvincia)) {
				provinciaMatched = provincia;
			}
		}
		return provinciaMatched;
	}

	public Location.Canton findCantonesByNombreCanton(String nombreCanton) {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Location.Canton> cantones = new ArrayList<Location.Canton>();
		for (Location location : locations) {
			for (Location.Provincia provincia : location.getProvincias()) {
				for (Location.Canton canton : provincia.getCantones()) {
					cantones.add(canton);
				}
			}
		}
		Location.Canton cantonMatched = null;
		for (Location.Canton canton : cantones) {
			if (canton.getNombreCanton().equals(nombreCanton)) {
				cantonMatched = canton;
			}
		}
		return cantonMatched;
	}

	public Location.Parroquia findParroquiasByNombreParroquia(String nombreParroquia) {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Location.Parroquia> parroquias = new ArrayList<Location.Parroquia>();
		for (Location location : locations) {
			for (Location.Provincia provincia : location.getProvincias()) {
				for (Location.Canton canton : provincia.getCantones()) {
					for (Location.Parroquia parroquia : canton.getParroquias()) {
						parroquias.add(parroquia);
					}
				}
			}
		}
		Location.Parroquia parroquiaMatched = null;
		for (Location.Parroquia parroquia : parroquias) {
			if (parroquia.getNombreParroquia().equals(nombreParroquia)) {
				parroquiaMatched = parroquia;
			}
		}
		return parroquiaMatched;
	}

	/*
	 * public Location addProvince(String id, Provincia province) {
	 * Location location = locationRepository.findById(id).orElse(null);
	 * if (location != null) {
	 * location.getProvincias().add(province);
	 * locationRepository.save(location);
	 * return location;
	 * }
	 * return null;
	 * }
	 */

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