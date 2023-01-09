package com.banquito.settings.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banquito.settings.model.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {

	List<Location> findByCountry(String country);

	List<Location> findByProvince(String province);

	List<Location> findByCanton(String canton);

	List<Location> findByParish(String parish);

	List<Location> findByCountryAndProvinceAndCantonAndParish(String country, String province, String canton,String parish);

}
