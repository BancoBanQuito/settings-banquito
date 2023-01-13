package com.banquito.settings.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchOfficeHour {

    private String mondayToFriday;
    private String saturday;

}
