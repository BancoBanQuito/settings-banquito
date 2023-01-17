package com.banquito.settings.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banquito.settings.model.Location;
import com.banquito.settings.model.Province;

public interface LocationRepository extends CrudRepository<Location, String> {

	Location findByProvincesProvinceName(String provinceName);

	Location findByProvincesCantonsCantonName(String cantonName);

	Location findByProvincesCantonsParishesParishName(String parishName);

	Boolean existsByProvincesProvinceName(String provinceName);

	List<Location> findAll();

	void save(List<Province> provinces);

}
