package com.example.EventBuddy.service;

import com.example.EventBuddy.VO.ResponseTemplate;
import com.example.EventBuddy.VO.Student;
import com.example.EventBuddy.entity.Events;
import com.example.EventBuddy.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<Events> getEvents() {
        return eventsRepository.findAll();
    }

    public Events createNewEvents(Events events) {
        Optional<Events> eventsOptional = eventsRepository
                .findEventsByEveId(events.getEveId());
        if (eventsOptional.isPresent()) {
            throw new IllegalStateException("Id already exists!");
        }
        return eventsRepository.save(events);
    }


    public Events deleteEvents(String eveId) {
        boolean exists = eventsRepository.existsById(eveId);
        if (!exists) {
            throw new IllegalStateException(
                    "Events with ID : " + eveId + " does not exist."
            );
        }
        eventsRepository.deleteById(eveId);

        return null;
    }

    public ResponseEntity<Events> updateEvents(String eveId, Events events) {
        Optional<Events> eventsOptional = eventsRepository.findById(eveId);
        if (eventsOptional.isPresent()) {
            Events _events = eventsOptional.get();
            _events.setEventName(events.getEventName());
            _events.setEventDescription(events.getEventDescription());
            _events.setEventVenue(events.getEventVenue());
            _events.setEventTime(events.getEventTime());
            _events.setEventDate(events.getEventDate());

            return new ResponseEntity<>(eventsRepository.save(_events), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Events getEventsByEveId(String eveId) {
        Optional<Events> eventsOptional = eventsRepository.findById(eveId);
        if (eventsOptional.isPresent()) {
            return eventsRepository.findByEveId(eveId);
        } else {
            throw new IllegalStateException("Events with ID" +
                    eveId + " does not exist.");
        }
    }

    public ResponseTemplate getEventByEveId(String eveId) {
        ResponseTemplate rtVO = new ResponseTemplate();
        Events event = eventsRepository.findByEveId(eveId);
        Student student = restTemplate.getForObject("http://localhost:8002/student/" + event.getStuId(), Student.class);
        //Events event = restTemplate.getForObject("http://localhost:8001/events/"+registration.getEveId(),Events.class);
        //rtVO.setRegistration(registration);
        rtVO.setEvents(event);
        rtVO.setStudent(student);
        return rtVO;
    }

    public String signin(Events event) {
        System.out.println(event.getEmail());
        System.out.println(event.getPassword());
        //Optional<Events> eventsOptional = eventsRepository.findEventsByEmail(event.getEmail());
//        String email = "admin@gmail.com";
//        String password = "admin";
        if (event.getEmail().equals("admin@gmail.com")) {
            if (event.getPassword().equals("admin")) {
                return "valid";
            }

        }
        return "invalid";
    }


    public void registerEventsByStudentId(Student student, String eveId) {
        Student _student = new Student();
        _student.setStuId(student.getStuId());
        Optional<Events> eventsOptional = eventsRepository.findById(eveId);
        if (eventsOptional.isPresent()) {
            Events _events = eventsOptional.get();
            List<String> studentList = _events.getStuId();
            List<String> newStudentList = new ArrayList<String>();
            for (String std : studentList) {
                // Check for ID in here
                newStudentList.add(std);
            }
            newStudentList.add(student.getStuId());
            _events.setStuId(newStudentList);
            eventsRepository.save(_events);


        }
    }
}
