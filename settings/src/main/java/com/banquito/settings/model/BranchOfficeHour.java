package com.banquito.settings.model;

import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchOfficeHour {

    private LocalTime openingTimeMondayFriday;
    private LocalTime closingTimeMondayFriday;
    private LocalTime openingTimeSaturday;
    private LocalTime closingTimeSaturday;

}
