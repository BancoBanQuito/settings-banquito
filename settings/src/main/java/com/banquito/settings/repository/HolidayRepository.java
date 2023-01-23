package com.banquito.settings.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.banquito.settings.model.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {

    List<Holiday> findByName(String name);

    List<Holiday> findByDate(Date date);
    List<Holiday> findByCodigoLocation(Integer codigoLocation);
}
