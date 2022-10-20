package com.example.EventBuddy.controller;


import com.example.EventBuddy.VO.Student;
import com.example.EventBuddy.entity.Events;
import com.example.EventBuddy.repository.EventsRepository;
import com.example.EventBuddy.service.EventsService;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*")
public class EventsController {

    private EventsService eventsService;


    @Autowired
    public EventsController(EventsService eventsService) {this.eventsService = eventsService;}

    @PostMapping
    public Events createEvents(@RequestBody Events eve){
        return eventsService.createNewEvents(eve);
    }

    @GetMapping
    public List<Events> getAllEvents(){
        return eventsService.getEvents();
    }

    @GetMapping(path = "{eveId}")
    public Events getEventsByEveId(@PathVariable("eveId") String eveId) {
        return eventsService.getEventsByEveId(eveId);
    }

    @PutMapping(path = "{eveId}")
    public void updateEvents(@PathVariable("eveId") String eveId, @RequestBody Events events){
        eventsService.updateEvents(eveId,events);
    }



    @DeleteMapping(path = "{eveId}")
    public Events deleteEvents(@PathVariable("eveId") String eveId){
        return eventsService.deleteEvents(eveId);
    }

    @PostMapping(path = "/signin")
    public String signin(@RequestBody Events event){
        System.out.println(event.getEmail());
        System.out.println(event.getPassword());
        return eventsService.signin(event);
    }

    @PostMapping(path = "/reg/{eveId}")
    public void registerEventByStudentId(@PathVariable("eveId") String eveId ,@RequestBody Student student){
         eventsService.registerEventsByStudentId(student, eveId);

    }

}
