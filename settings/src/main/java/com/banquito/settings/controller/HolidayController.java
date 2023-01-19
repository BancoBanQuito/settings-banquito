package com.banquito.settings.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.controller.dto.HolidayRQ;
import com.banquito.settings.controller.dto.HolidayRS;
import com.banquito.settings.controller.mapper.HolidayMapper;
import com.banquito.settings.model.Holiday;
import com.banquito.settings.service.HolidayService;

@RestController
@RequestMapping("/api/holiday")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object findAll() {
        Iterable<Holiday> holidays = this.holidayService.findAll();
        List<HolidayRS> holidaysRS = new ArrayList<>();
        for (Holiday holiday : holidays)
            holidaysRS.add(HolidayMapper.toHolidayRS(holiday));
        if (holidaysRS.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(holidaysRS);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Object findByName(@PathVariable("name") String name) {
        Iterable<Holiday> holidays = this.holidayService.findByName(name);
        List<HolidayRS> holidaysRS = new ArrayList<>();
        for (Holiday holiday : holidays)
            holidaysRS.add(HolidayMapper.toHolidayRS(holiday));
        if (holidaysRS.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(holidaysRS);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object createHoliday(@RequestBody HolidayRQ holidayRQ) {
        try {
            this.holidayService.createHoliday(HolidayMapper.toHoliday(holidayRQ));
            return ResponseEntity.ok("Holiday created successfully");

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{year}", method = RequestMethod.POST)
    public ResponseEntity<String> generateWeekendHoliday(@PathVariable("year") int year) {
        try {
            this.holidayService.generateHolidayByYear(year);
            return ResponseEntity.ok("Weekend Holiday created successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
	public Object updateHoliday(@RequestBody HolidayRQ holidayRQ) {
        try {
            this.holidayService.updateHoliday(HolidayMapper.toHoliday(holidayRQ));
            return ResponseEntity.ok("Holiday updated successfully");

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("There is no Holidays");
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
	public Object deleteHoliday(@RequestBody HolidayRQ holidayRQ) {
        try {
            this.holidayService.deleteHoliday(HolidayMapper.toHoliday(holidayRQ));
            return ResponseEntity.ok("Holiday deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("There is no Holidays");
        }
    }

}
