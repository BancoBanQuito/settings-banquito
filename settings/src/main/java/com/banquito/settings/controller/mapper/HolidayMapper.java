package com.banquito.settings.controller.mapper;

import com.banquito.settings.controller.dto.HolidayRQ;
import com.banquito.settings.controller.dto.HolidayRS;
import com.banquito.settings.model.Holiday;

public class HolidayMapper {

    public static Holiday toHoliday(HolidayRQ rq){
        return Holiday.builder().date(rq.getDate()).name(rq.getName()).type(rq.getType()).build();
    }
    
    public static HolidayRS toHolidayRS(Holiday holiday){
        return HolidayRS.builder().date(holiday.getDate()).name(holiday.getName()).type(holiday.getType()).build();
    }
}
