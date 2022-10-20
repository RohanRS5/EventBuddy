package com.example.EventBuddy.repository;


import com.example.EventBuddy.entity.Events;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventsRepository extends MongoRepository<Events, String> {
    Events findByEveId(String eveId);

    Optional<Events> findEventsByEveId(String eveId);

    Optional<Events> findEventsByEmail(String email);
}
