package com.example.EventBuddy.VO;




import com.example.EventBuddy.entity.Events;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {

    private Events events;
    private Student student;

}
