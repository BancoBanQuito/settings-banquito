package com.banquito.settings.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.settings.model.HolidayF;

@Repository
public interface HolidayFRepository extends CrudRepository<HolidayF, String> {

    List<HolidayF> findByDate(Date date);

    

    
}
