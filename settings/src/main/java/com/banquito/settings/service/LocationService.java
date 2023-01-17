package com.banquito.settings.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.Canton;
import com.banquito.settings.model.Location;
import com.banquito.settings.model.Parroquia;
import com.banquito.settings.model.Provincia;
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

	public List<Provincia> findAllProvincias() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Provincia> provincias = new ArrayList<Provincia>();
		for (Location location : locations) {
			for (Provincia provincia : location.getProvincias()) {
				provincias.add(provincia);
			}
		}
		return provincias;
	}

	public List<Canton> findAllCantones() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Canton> cantones = new ArrayList<Canton>();
		for (Location location : locations) {
			for (Provincia provincia : location.getProvincias()) {
				for (Canton canton : provincia.getCantones()) {
					cantones.add(canton);
				}
			}
		}
		return cantones;
	}

	public List<Parroquia> findAllParroquias() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Parroquia> parroquias = new ArrayList<Parroquia>();
		for (Location location : locations) {
			for (Provincia provincia : location.getProvincias()) {
				for (Canton canton : provincia.getCantones()) {
					for (Parroquia parroquia : canton.getParroquias()) {
						parroquias.add(parroquia);
					}
				}
			}
		}
		return parroquias;
	}

	public Provincia findProvinciasByNombreProvincia(String nombreProvincia) {
		Location location = locationRepository.findByProvinciasNombreProvincia(nombreProvincia);
		List<Provincia> provincias = location.getProvincias();
		Provincia provinciaMatched = null;
		for (Provincia provincia : provincias) {
			if (provincia.getNombreProvincia().equals(nombreProvincia)) {
				provinciaMatched = provincia;
			}
		}
		return provinciaMatched;
	}

	public Canton findCantonesByNombreCanton(String nombreCanton) {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Canton> cantones = new ArrayList<Canton>();
		for (Location location : locations) {
			for (Provincia provincia : location.getProvincias()) {
				for (Canton canton : provincia.getCantones()) {
					cantones.add(canton);
				}
			}
		}
		Canton cantonMatched = null;
		for (Canton canton : cantones) {
			if (canton.getNombreCanton().equals(nombreCanton)) {
				cantonMatched = canton;
			}
		}
		return cantonMatched;
	}

	public Parroquia findParroquiasByNombreParroquia(String nombreParroquia) {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Parroquia> parroquias = new ArrayList<Parroquia>();
		for (Location location : locations) {
			for (Provincia provincia : location.getProvincias()) {
				for (Canton canton : provincia.getCantones()) {
					for (Parroquia parroquia : canton.getParroquias()) {
						parroquias.add(parroquia);
					}
				}
			}
		}
		Parroquia parroquiaMatched = null;
		for (Parroquia parroquia : parroquias) {
			if (parroquia.getNombreParroquia().equals(nombreParroquia)) {
				parroquiaMatched = parroquia;
			}
		}
		return parroquiaMatched;
	}

	public Map<String, Object> getProvinciaCantonParroquia(String nombreProvincia, String nombreCanton,
			String nombreParroquia) {
		Map<String, Object> response = new HashMap<>();
		response.put("nombreProvincia", findProvinciasByNombreProvincia(nombreProvincia).getNombreProvincia());
		response.put("nombreCanton", findCantonesByNombreCanton(nombreCanton).getNombreCanton());
		response.put("nombreParroquia", findParroquiasByNombreParroquia(nombreParroquia).getNombreParroquia());
		return response;
	}

	@Transactional
	public void createProvincia(String id, Provincia provincia) {
		Location location = locationRepository.findById(id).get();
		location.getProvincias().add(provincia);
		this.locationRepository.save(location);
	}

	@Transactional
	public void createCanton(String provinceName, Canton canton) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").get();
		Optional<Provincia> existingProvince = existingLocation.getProvincias().stream()
				.filter(provincia -> provincia.getNombreProvincia() != null
						&& provincia.getNombreProvincia().equals(provinceName))
				.findFirst();
		if (existingProvince.isPresent()) {
			if (existingProvince.get().getCantones() == null) {
				existingProvince.get().setCantones(new ArrayList<>());
			}
			existingProvince.get().getCantones().add(canton);
			locationRepository.save(existingLocation);
		} else {
			throw new RuntimeException("Province not found");
		}
	}

	@Transactional
	public void createParroquia(String provinceName, String cantonName, Parroquia parroquia) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").get();
		Optional<Provincia> existingProvince = existingLocation.getProvincias().stream()
				.filter(provincia -> provincia.getNombreProvincia() != null
						&& provincia.getNombreProvincia().equals(provinceName))
				.findFirst();
		if (existingProvince.isPresent()) {
			Optional<Canton> existingCanton = existingProvince.get().getCantones().stream()
					.filter(canton -> canton.getNombreCanton() != null
							&& canton.getNombreCanton().equals(cantonName))
					.findFirst();
			if (existingCanton.isPresent()) {
				if (existingCanton.get().getParroquias() == null) {
					existingCanton.get().setParroquias(new ArrayList<>());
				}
				existingCanton.get().getParroquias().add(parroquia);
				locationRepository.save(existingLocation);
			} else {
				throw new RuntimeException("Canton not found");
			}
		} else {
			throw new RuntimeException("Province not found");
		}
	}

	@Transactional
	public void updateProvincia(String id, String nombreProvincia, String newNombreProvincia) {
		Location existingLocation = this.locationRepository.findById(id).orElse(null);
		if (existingLocation != null) {

			Optional<Provincia> existingProvince = existingLocation.getProvincias().stream()
					.filter(provincia -> provincia.getNombreProvincia() != null
							&& provincia.getNombreProvincia().equals(nombreProvincia))
					.findFirst();

			if (existingProvince.isPresent()) {
				existingProvince.get().setNombreProvincia(newNombreProvincia);
				this.locationRepository.save(existingLocation);
			}
		}
	}

	@Transactional
	public void updateCanton(String nombreCanton, String newNombreCanton) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").orElseThrow();
		Optional<Provincia> existingProvince = existingLocation.getProvincias().stream()
				.filter(province -> province.getCantones().stream()
						.anyMatch(canton -> canton.getNombreCanton().equals(nombreCanton)))
				.findFirst();
		if (existingProvince.isPresent()) {
			existingProvince.get().getCantones().stream()
					.filter(canton -> canton.getNombreCanton().equals(nombreCanton))
					.findFirst()
					.ifPresent(canton -> canton.setNombreCanton(newNombreCanton));
		}
		locationRepository.save(existingLocation);
	}

	public Location updateParroquia(String id, String parroquiaName, Parroquia parroquia) {
		Optional<Location> locationOptional = locationRepository.findById(id);
		if (locationOptional.isPresent()) {
			Location existingLocation = locationOptional.get();
			for (Provincia provincia : existingLocation.getProvincias()) {
				for (Canton canton : provincia.getCantones()) {
					for (Iterator<Parroquia> iterator = canton.getParroquias().iterator(); iterator
							.hasNext();) {
						Parroquia existingParroquia = iterator.next();
						if (existingParroquia.getNombreParroquia().equals(parroquiaName)) {
							existingParroquia.setNombreParroquia(parroquia.getNombreParroquia());
							existingParroquia.setCodigoPostal(parroquia.getCodigoPostal());
							return locationRepository.save(existingLocation);
						}
					}
				}
			}
		}
		return null;
	}

	public void deleteProvincia(String id, String nombreProvincia) {
		Location existingLocation = this.locationRepository.findById(id).orElse(null);
		if (existingLocation != null) {
			Optional<Provincia> existingProvince = existingLocation.getProvincias().stream()
					.filter(provincia -> provincia.getNombreProvincia() != null
							&& provincia.getNombreProvincia().equals(nombreProvincia))
					.findFirst();
			if (existingProvince.isPresent()) {
				existingLocation.getProvincias().remove(existingProvince.get());
				this.locationRepository.save(existingLocation);
			}
		}
	}

	public void deleteCanton(String nombreCanton) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").orElseThrow();
		Optional<Provincia> existingProvince = existingLocation.getProvincias().stream()
				.filter(province -> province.getCantones().stream()
						.anyMatch(canton -> canton.getNombreCanton().equals(nombreCanton)))
				.findFirst();
		if (existingProvince.isPresent()) {
			existingProvince.get().getCantones().removeIf(canton -> canton.getNombreCanton().equals(nombreCanton));
		}
		locationRepository.save(existingLocation);
	}

	public void deleteParroquia(String id, String parroquiaName) {
		Optional<Location> locationOptional = locationRepository.findById(id);
		if (locationOptional.isPresent()) {
			Location existingLocation = locationOptional.get();
			for (Provincia provincia : existingLocation.getProvincias()) {
				for (Canton canton : provincia.getCantones()) {
					for (Iterator<Parroquia> iterator = canton.getParroquias().iterator(); iterator
							.hasNext();) {
						Parroquia existingParroquia = iterator.next();
						if (existingParroquia.getNombreParroquia().equals(parroquiaName)) {
							iterator.remove();
							locationRepository.save(existingLocation);
						}
					}
				}
			}
		}
	}

	public Location save(Location location) {
		return locationRepository.save(location);
	}

	public void delete(Location location) {
		locationRepository.delete(location);
	}
}