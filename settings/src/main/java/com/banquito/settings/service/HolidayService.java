package com.banquito.settings.service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.Holiday;
import com.banquito.settings.repository.HolidayRepository;

@Service
public class HolidayService {

    public int hora = 0;
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

    public List<Holiday> findByCodeLocation(Integer code){
        return holidayRepository.findByCodigoLocation(code);
    }

    @Transactional
    public void createHoliday(Holiday holiday) {
        

        List<Holiday> holidays = this.holidayRepository.findByDate(holiday.getDate());
        if (holidays.isEmpty()) {
            if(holiday.getType().equals("Regional") || holiday.getType().equals("regional") )
            holiday.setType("REG");
            if(holiday.getType().equals("Nacional") || holiday.getType().equals("nacional"))
            holiday.setType("NAT");
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
        if(holiday.getType().equals("Regional") || holiday.getType().equals("regional") )
            holidays.setType("REG");
            if(holiday.getType().equals("Nacional") || holiday.getType().equals("nacional"))
            holidays.setType("NAT");
        holidays.setCodigoLocation(holiday.getCodigoLocation());
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

    public void generateHolidayByYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1);
        
        calendar.set(Calendar.HOUR_OF_DAY,hora);
        calendar.set(Calendar.HOUR,hora);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        Date sunday;
        Date saturday;
        while ((isDay(date, "sábado") || isDay(date, "domingo"))) {
            date = addDaysToDate(date, 1);
            System.out.println(date);
        }

        if (isDay(date, "sábado")) {
            sunday = date;
            saturday = addDaysToDate(date, 1);
        } else {

            Holiday holiday = new Holiday();
            holiday.setDate(date);
            holiday.setName("FIN DE SEMANA");
            holiday.setCodigoLocation(17);
            holiday.setType("NAT");
            createHoliday(holiday);

            sunday = addDaysToDate(date, 6);
            saturday = addDaysToDate(date, 7);
        }

        String yearHoliday;
        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");

        do {
            yearHoliday = getYearFormat.format(sunday);
            if (Integer.parseInt(yearHoliday) <= year) {
                Holiday sundayHoliday = new Holiday();
                sundayHoliday.setDate(sunday);
                sundayHoliday.setCodigoLocation(17);
                sundayHoliday.setName("FIN DE SEMANA");
                sundayHoliday.setType("NAT");
                createHoliday(sundayHoliday);
            }

            yearHoliday = getYearFormat.format(saturday);
            if (Integer.parseInt(yearHoliday) <= year) {
                Holiday saturdayHoliday = new Holiday();
                saturdayHoliday.setDate(saturday);
                saturdayHoliday.setCodigoLocation(17);
                saturdayHoliday.setName("FIN DE SEMANA");
                saturdayHoliday.setType("NAT");
                createHoliday(saturdayHoliday);
            }

            sunday = addDaysToDate(sunday, 7);
            saturday = addDaysToDate(saturday, 7);

        } while (Integer.parseInt(yearHoliday) <= year);

    }

    private Date addDaysToDate(Date date, int dias) {
        if (dias == 0)
            return date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,hora);
        calendar.set(Calendar.HOUR,hora);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    private boolean isDay(Date date, String day) {
        String dateString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        dateString = dateFormat.format(date);
        System.out.println(dateString);
        return dateString.indexOf(day) >= 0;
    }

    

}
