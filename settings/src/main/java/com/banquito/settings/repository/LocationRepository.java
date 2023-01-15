package com.banquito.settings.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banquito.settings.model.Location;

public interface LocationRepository extends CrudRepository<Location, String> {

	Location findByProvinciasNombreProvincia(String nombreProvincia);

	Location findByProvinciasCantonesNombreCanton(String nombreCanton);

	Location findByProvinciasCantonesParroquiasNombreParroquia(String nombreParroquia);

	Boolean existsByProvinciasNombreProvincia(String nombreProvincia);

	void save(List<Location.Provincia> provincia);

}
