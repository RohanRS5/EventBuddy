package com.example.StudentService.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;



@Document(collection = "students")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Indexed(unique = true)
    @Id
    private String stuId;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String password;

    private String studentName;

    private String studentDepartment;

    private String studentDivision;

    private String studentConsent;


}
