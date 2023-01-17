package com.banquito.settings.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.controller.dto.HolidayRQ;
import com.banquito.settings.controller.mapper.HolidayMapper;
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
