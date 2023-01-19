package com.banquito.settings.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "holidays")
public class HolidayF {

    @Id
    private String id;
    
    private Date date;
    private String name;
    private String type;

    @Version
    private Integer version;

}
