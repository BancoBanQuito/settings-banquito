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
import com.banquito.settings.model.Parish;
import com.banquito.settings.model.Province;
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

	public List<Province> findAllProvinces() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Province> parishes = new ArrayList<Province>();
		for (Location location : locations) {
			for (Province province : location.getProvinces()) {
				parishes.add(province);
			}
		}
		return parishes;
	}

	public List<Canton> findAllCantons() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Canton> cantons = new ArrayList<Canton>();
		for (Location location : locations) {
			for (Province province : location.getProvinces()) {
				for (Canton canton : province.getCantons()) {
					cantons.add(canton);
				}
			}
		}
		return cantons;
	}

	public List<Parish> findAllParishes() {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Parish> parishes = new ArrayList<Parish>();
		for (Location location : locations) {
			for (Province province : location.getProvinces()) {
				for (Canton canton : province.getCantons()) {
					for (Parish parish : canton.getParishes()) {
						parishes.add(parish);
					}
				}
			}
		}
		return parishes;
	}

	public Province findProvincesByProvinceName(String provinceName) {
		Location location = locationRepository.findByProvincesProvinceName(provinceName);
		List<Province> parishes = location.getProvinces();
		Province provinceMatched = null;
		for (Province province : parishes) {
			if (province.getProvinceName().equals(provinceName)) {
				provinceMatched = province;
			}
		}
		return provinceMatched;
	}

	public Canton findCantonsByCantonName(String cantonName) {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Canton> cantons = new ArrayList<Canton>();
		for (Location location : locations) {
			for (Province province : location.getProvinces()) {
				for (Canton canton : province.getCantons()) {
					cantons.add(canton);
				}
			}
		}
		Canton cantonMatched = null;
		for (Canton canton : cantons) {
			if (canton.getCantonName().equals(cantonName)) {
				cantonMatched = canton;
			}
		}
		return cantonMatched;
	}

	public Parish findParishesByParishName(String parishName) {
		List<Location> locations = (List<Location>) locationRepository.findAll();
		List<Parish> Parishes = new ArrayList<Parish>();
		for (Location location : locations) {
			for (Province province : location.getProvinces()) {
				for (Canton canton : province.getCantons()) {
					for (Parish parish : canton.getParishes()) {
						Parishes.add(parish);
					}
				}
			}
		}
		Parish parishMatched = null;
		for (Parish parish : Parishes) {
			if (parish.getParishName().equals(parishName)) {
				parishMatched = parish;
			}
		}
		return parishMatched;
	}

	public Map<String, Object> getProvinceCantonParish(String provinceName, String cantonName,
			String parishName) {
		Map<String, Object> response = new HashMap<>();
		response.put("provinceName", findProvincesByProvinceName(provinceName).getProvinceName());
		response.put("cantonName", findCantonsByCantonName(cantonName).getCantonName());
		response.put("parishName", findParishesByParishName(parishName).getParishName());
		return response;
	}

	@Transactional
	public void createProvince(String id, Province province) {
		Location location = locationRepository.findById(id).get();
		province.setCantons(new ArrayList<>());
		location.getProvinces().add(province);
		this.locationRepository.save(location);
	}

	@Transactional
	public void createCanton(String provinceName, Canton canton) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").get();
		Optional<Province> existingProvince = existingLocation.getProvinces().stream()
				.filter(province -> province.getProvinceName() != null
						&& province.getProvinceName().equals(provinceName))
				.findFirst();
		if (existingProvince.isPresent()) {
			if (existingProvince.get().getCantons() == null) {
				existingProvince.get().setCantons(new ArrayList<>());
			}
			existingProvince.get().getCantons().add(canton);
			locationRepository.save(existingLocation);
		} else {
			throw new RuntimeException("Province not found");
		}
	}

	@Transactional
	public void createParish(String provinceName, String cantonName, Parish parish) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").get();
		Optional<Province> existingProvince = existingLocation.getProvinces().stream()
				.filter(province -> province.getProvinceName() != null
						&& province.getProvinceName().equals(provinceName))
				.findFirst();
		if (existingProvince.isPresent()) {
			Optional<Canton> existingCanton = existingProvince.get().getCantons().stream()
					.filter(canton -> canton.getCantonName() != null
							&& canton.getCantonName().equals(cantonName))
					.findFirst();
			if (existingCanton.isPresent()) {
				if (existingCanton.get().getParishes() == null) {
					existingCanton.get().setParishes(new ArrayList<>());
				}
				existingCanton.get().getParishes().add(parish);
				locationRepository.save(existingLocation);
			} else {
				throw new RuntimeException("Canton not found");
			}
		} else {
			throw new RuntimeException("Province not found");
		}
	}

	@Transactional
	public void updateProvince(String id, String provinceName, String newProvinceName) {
		Location existingLocation = this.locationRepository.findById(id).orElse(null);
		if (existingLocation != null) {

			Optional<Province> existingProvince = existingLocation.getProvinces().stream()
					.filter(province -> province.getProvinceName() != null
							&& province.getProvinceName().equals(provinceName))
					.findFirst();

			if (existingProvince.isPresent()) {
				existingProvince.get().setProvinceName(newProvinceName);
				this.locationRepository.save(existingLocation);
			}
		}
	}

	@Transactional
	public void updateCanton(String cantonName, String newCantonName) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").orElseThrow();
		Optional<Province> existingProvince = existingLocation.getProvinces().stream()
				.filter(province -> province.getCantons().stream()
						.anyMatch(canton -> canton.getCantonName().equals(cantonName)))
				.findFirst();
		if (existingProvince.isPresent()) {
			existingProvince.get().getCantons().stream()
					.filter(canton -> canton.getCantonName().equals(cantonName))
					.findFirst()
					.ifPresent(canton -> canton.setCantonName(newCantonName));
		}
		locationRepository.save(existingLocation);
	}

	public Location updateParish(String id, String parishName, Parish parish) {
		Optional<Location> locationOptional = locationRepository.findById(id);
		if (locationOptional.isPresent()) {
			Location existingLocation = locationOptional.get();
			for (Province province : existingLocation.getProvinces()) {
				for (Canton canton : province.getCantons()) {
					for (Iterator<Parish> iterator = canton.getParishes().iterator(); iterator
							.hasNext();) {
						Parish existingParish = iterator.next();
						if (existingParish.getParishName().equals(parishName)) {
							existingParish.setParishName(parish.getParishName());
							existingParish.setZipCode(parish.getZipCode());
							return locationRepository.save(existingLocation);
						}
					}
				}
			}
		}
		return null;
	}

	public void deleteProvince(String id, String provinceName) {
		Location existingLocation = this.locationRepository.findById(id).orElse(null);
		if (existingLocation != null) {
			Optional<Province> existingProvince = existingLocation.getProvinces().stream()
					.filter(province -> province.getProvinceName() != null
							&& province.getProvinceName().equals(provinceName))
					.findFirst();
			if (existingProvince.isPresent()) {
				existingLocation.getProvinces().remove(existingProvince.get());
				this.locationRepository.save(existingLocation);
			}
		}
	}

	public void deleteCanton(String cantonName) {
		Location existingLocation = locationRepository.findById("63c424969696e95c3534f89b").orElseThrow();
		Optional<Province> existingProvince = existingLocation.getProvinces().stream()
				.filter(province -> province.getCantons().stream()
						.anyMatch(canton -> canton.getCantonName().equals(cantonName)))
				.findFirst();
		if (existingProvince.isPresent()) {
			existingProvince.get().getCantons().removeIf(canton -> canton.getCantonName().equals(cantonName));
		}
		locationRepository.save(existingLocation);
	}

	public void deleteParish(String id, String parishName) {
		Optional<Location> locationOptional = locationRepository.findById(id);
		if (locationOptional.isPresent()) {
			Location existingLocation = locationOptional.get();
			for (Province province : existingLocation.getProvinces()) {
				for (Canton canton : province.getCantons()) {
					for (Iterator<Parish> iterator = canton.getParishes().iterator(); iterator
							.hasNext();) {
						Parish existingParish = iterator.next();
						if (existingParish.getParishName().equals(parishName)) {
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