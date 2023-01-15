package com.banquito.settings.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Document(collection = "holidays")
public class Holiday {

    @Id
    private String id;
    
    private Date date;
    private String name;
    private String type;

    @Version
    private Integer version;

}
