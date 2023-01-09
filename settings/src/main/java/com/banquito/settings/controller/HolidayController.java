package com.banquito.settings.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.banquito.settings.model.Holiday;
import com.banquito.settings.service.HolidayService;

@RestController
@RequestMapping("/api/holiday")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService){
        this.holidayService = holidayService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object findAll() {
        return ResponseEntity.ok(this.holidayService.findAll());
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<Holiday>> getHolidayByDate(@PathVariable("date") String date) {
        return ResponseEntity.ok(this.holidayService.findByDate(date));
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Holiday>> getHolidayByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.holidayService.findByName(name));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> createHoliday(@RequestBody Holiday holiday) {
            return ResponseEntity.ok("Holiday created successfully");
    }

    @RequestMapping(value = "/{holiday}", method = RequestMethod.PUT)
    public Object updateHoliday(@PathVariable("holiday") String date, @RequestBody Holiday holiday) {
        return ResponseEntity.ok("Holiday updated successfully");
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.DELETE)
    public Object deleteHoliday(@PathVariable("date") String date, @RequestBody Holiday holiday) {
        return ResponseEntity.ok("Holiday deleted successfully");
    }
    
}
