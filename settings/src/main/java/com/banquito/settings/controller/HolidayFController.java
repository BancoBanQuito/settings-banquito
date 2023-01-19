package com.banquito.settings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.service.HolidayFService;

@RestController
@RequestMapping("/api/holiday")
public class HolidayFController {


    private final HolidayFService holidayFService;

    public HolidayFController(HolidayFService holidayFService) {
        this.holidayFService = holidayFService;
    }

    @RequestMapping(value = "/{year}", method = RequestMethod.POST)
    public ResponseEntity<String> generateWeekendHoliday(@PathVariable("year") int year) {
        try {
            this.holidayFService.generateHolidayByYear(year);
            return ResponseEntity.ok("Weekend Holiday created successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
}
