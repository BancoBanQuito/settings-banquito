package com.banquito.settings.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.banquito.settings.model.HolidayF;
import com.banquito.settings.repository.HolidayFRepository;


@Service
public class HolidayFService {
    public int hora = 0;

    private final HolidayFRepository holidayFRepository;

    public HolidayFService(HolidayFRepository holidayFRepository) {
        this.holidayFRepository = holidayFRepository;
    }

   
    @Transactional
    public void createHoliday(HolidayF holiday) {

        List<HolidayF> holidays = this.holidayFRepository.findByDate(holiday.getDate());
        if (holidays.isEmpty()) {

            this.holidayFRepository.save(holiday);
        } else {
            throw new RuntimeException("The Holiday already exists");
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

            HolidayF holiday = new HolidayF();
            holiday.setDate(date);
            holiday.setName("Weekend Holiday");
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
                HolidayF sundayHoliday = new HolidayF();
                sundayHoliday.setDate(sunday);
                
                sundayHoliday.setName("Weekend Holiday");
                sundayHoliday.setType("NAT");
                createHoliday(sundayHoliday);
            }

            yearHoliday = getYearFormat.format(saturday);
            if (Integer.parseInt(yearHoliday) <= year) {
                HolidayF saturdayHoliday = new HolidayF();
                saturdayHoliday.setDate(saturday);
                saturdayHoliday.setName("Weekend Holiday");
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
