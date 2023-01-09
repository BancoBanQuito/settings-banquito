package com.banquito.settings.service;


import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.Holiday;
import com.banquito.settings.repository.HolidayRepository;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;
    
    public HolidayService(HolidayRepository holidayRepository){
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> findAll(){
        return holidayRepository.findAll();
    }

    public List<Holiday> findByNameLike(String name){
        return holidayRepository.findByName(name);
    }

    public List<Holiday> findByDateLike(String date){
        return holidayRepository.findByDate(date);
    }
    @Transactional
    public void createHoliday(Holiday holiday){
        this.holidayRepository.save(holiday);
    }

    @Transactional
    public void updateBranch(String date, Holiday holiday){
        this.holidayRepository.save(holiday);
    }

    @Transactional
    public void deleteHoliday(String code, Holiday holiday){
        this.holidayRepository.delete(holiday);
    }



    
}
