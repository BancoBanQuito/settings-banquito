package com.banquito.settings.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.Holiday;
import com.banquito.settings.repository.HolidayRepository;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public Iterable<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    public List<Holiday> findByName(String name) {
        return holidayRepository.findByName(name);
    }

    @Transactional
    public void createHoliday(Holiday holiday) {

        List<Holiday> holidays = this.holidayRepository.findByDate(holiday.getDate());
        if (holidays.isEmpty()) {

            this.holidayRepository.save(holiday);
        } else {
            throw new RuntimeException("The Holiday already exists");
        }
    }

}
