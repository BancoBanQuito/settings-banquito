package com.banquito.settings.controller.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.banquito.settings.controller.dto.HolidayRQ;
import com.banquito.settings.controller.dto.HolidayRS;
import com.banquito.settings.model.Holiday;

public class HolidayMapper {

    public static Holiday toHoliday(HolidayRQ rq) throws ParseException {
        Date date = rq.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateF = dateFormat2.parse(dateString);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateF);

        cal.add(Calendar.DATE, 0);
        cal.add(Calendar.HOUR, 0);
        Date dateFinal = cal.getTime();

        return Holiday.builder().date(dateFinal).name(rq.getName().toUpperCase()).codigoLocation(rq.getCode())
                .type(rq.getType()).build();

    }

    public static HolidayRS toHolidayRS(Holiday holiday) {
        return HolidayRS.builder().date(holiday.getDate()).code(holiday.getCodigoLocation())
                .name(holiday.getName().toUpperCase()).type(holiday.getType()).build();
    }
}
