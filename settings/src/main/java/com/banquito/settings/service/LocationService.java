package com.banquito.settings.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

	public Object getProvinciaCantonParroquia(String nombreProvincia, String nombreCanton, String nombreParroquia) {
		String provincia = findProvinciasByNombreProvincia(nombreProvincia).getNombreProvincia();
		String canton = findCantonesByNombreCanton(nombreCanton).getNombreCanton();
		String parroquia = findParroquiasByNombreParroquia(nombreParroquia).getNombreParroquia();
		return new Object() {
			public final String nombreProvincia = provincia;
			public final String nombreCanton = canton;
			public final String nombreParroquia = parroquia;
		};

	}

	@Transactional
	public void createProvincia(String id, Location.Provincia provincia) {
		Location location = locationRepository.findById(id).get();
		location.getProvincias().add(provincia);
		this.locationRepository.save(location);
	}

	@Transactional
	public void createCanton(String provinceName, Location.Canton canton) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").get();
		Optional<Location.Provincia> existingProvince = existingLocation.getProvincias().stream()
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
	public void createParroquia(String provinceName, String cantonName, Location.Parroquia parroquia) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").get();
		Optional<Location.Provincia> existingProvince = existingLocation.getProvincias().stream()
				.filter(provincia -> provincia.getNombreProvincia() != null
						&& provincia.getNombreProvincia().equals(provinceName))
				.findFirst();
		if (existingProvince.isPresent()) {
			Optional<Location.Canton> existingCanton = existingProvince.get().getCantones().stream()
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

			Optional<Location.Provincia> existingProvince = existingLocation.getProvincias().stream()
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
		Optional<Location.Provincia> existingProvince = existingLocation.getProvincias().stream()
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

	public Location updateParroquia(String id, String parroquiaName, Location.Parroquia parroquia) {
		Optional<Location> locationOptional = locationRepository.findById(id);
		if (locationOptional.isPresent()) {
			Location existingLocation = locationOptional.get();
			for (Location.Provincia provincia : existingLocation.getProvincias()) {
				for (Location.Canton canton : provincia.getCantones()) {
					for (Iterator<Location.Parroquia> iterator = canton.getParroquias().iterator(); iterator
							.hasNext();) {
						Location.Parroquia existingParroquia = iterator.next();
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
			Optional<Location.Provincia> existingProvince = existingLocation.getProvincias().stream()
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
		Optional<Location.Provincia> existingProvince = existingLocation.getProvincias().stream()
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
			for (Location.Provincia provincia : existingLocation.getProvincias()) {
				for (Location.Canton canton : provincia.getCantones()) {
					for (Iterator<Location.Parroquia> iterator = canton.getParroquias().iterator(); iterator
							.hasNext();) {
						Location.Parroquia existingParroquia = iterator.next();
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