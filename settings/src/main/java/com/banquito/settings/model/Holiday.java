package com.banquito.settings.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "holiday")
public class Holiday {

    @Id
    private Date date;

    @Field(value="code_location")
    private Integer locationCode;

    @Field(value="name")
    private String name;

    
    @Field(value="type")
    private String type;
}
