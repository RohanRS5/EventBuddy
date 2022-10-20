package com.example.EventBuddy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;




import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Events {

    @Id
    @Indexed(unique = true)
    private String eveId;

    private String eventName;


    private String email;

    private String password;


    private String eventDescription;


    private String eventVenue;


    private String eventTime;


    private String eventDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<String>  stuId = new ArrayList<>();

}
