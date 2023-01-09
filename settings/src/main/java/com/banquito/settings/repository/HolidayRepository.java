package com.banquito.settings.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.banquito.settings.model.Holiday;

@Repository
public interface HolidayRepository extends MongoRepository<Holiday, Date>{
    
    List<Holiday> findByName(String name);

    List<Holiday> findByDate(String date);
    
}
