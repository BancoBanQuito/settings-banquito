package com.banquito.settings.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banquito.settings.model.Location;

public interface LocationRepository extends CrudRepository<Location, String> {

	List<Location> findByProvincia(String provincia);

	List<Location> findByCanton(String canton);

	List<Location> findByParroquia(String parroquia);

}
