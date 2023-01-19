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

    @Transactional
	public void updateHoliday(Holiday holiday) {
		Holiday holidays = this.holidayRepository.findByDate(holiday.getDate()).iterator().next();
		
        List<Holiday> holidayList = this.holidayRepository.findByDate(holiday.getDate());
        if (holidayList.isEmpty()) {

            throw new RuntimeException("There is no Holidays");
        } else {
            holidays.setDate(holiday.getDate());
		holidays.setName(holiday.getName());
        holidays.setType(holiday.getType());
		this.holidayRepository.save(holidays);
        }
	}

    @Transactional
	public void deleteHoliday(Holiday holiday) {
		Holiday holidays = this.holidayRepository.findByDate(holiday.getDate()).iterator().next();
		
        List<Holiday> holidayList = this.holidayRepository.findByDate(holiday.getDate());
        if (holidayList.isEmpty()) {

            throw new RuntimeException("There is no Holidays");
        } else {
            
		this.holidayRepository.delete(holidays);
        }
	}

}
