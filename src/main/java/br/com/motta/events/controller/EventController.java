package br.com.motta.events.controller;

import br.com.motta.events.dto.ErrorMessage;
import br.com.motta.events.exception.InformationNullException;
import br.com.motta.events.model.Event;
import br.com.motta.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/events")
    public ResponseEntity<?> addNewEvent(@RequestBody Event newEvent){
        try {
            return service.addNewEvent(newEvent);
        } catch (InformationNullException e){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ErrorMessage(e.getMessage()));
        }
    }

    @GetMapping("/events")
    public List<Event> getAllEvents(){
        return service.getAllEvents();
    }

    @GetMapping("/events/{prettyName}")
    public ResponseEntity<Event> getEventByPrettyName(@PathVariable String prettyName){
        Event evt = service.getByPrettyName(prettyName);
        if (evt != null){
            return ResponseEntity.status(200).body(evt);
        }
        return ResponseEntity.notFound().build();
    }

}
