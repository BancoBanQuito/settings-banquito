package com.banquito.settings.repository;

import org.springframework.data.repository.CrudRepository;

import com.banquito.settings.model.Location;

public interface LocationRepository extends CrudRepository<Location, String> {

	Location findByProvinciasNombreProvincia(String nombreProvincia);

	Location findByProvinciasCantonesNombreCanton(String nombreCanton);

	Location findByProvinciasCantonesParroquiasNombreParroquia(String nombreParroquia);

}
