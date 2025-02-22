package br.com.motta.events.service;

import br.com.motta.events.exception.InformationNullException;
import br.com.motta.events.model.Event;
import br.com.motta.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public ResponseEntity<?> addNewEvent(Event event){
        if (event.getTitle() == null || event.getLocation() == null || event.getPrice() == null
                || event.getStartDate() == null || event.getEndDate() == null || event.getStartTime() == null || event.getEndTime() == null){
            throw new InformationNullException("Informações incompletas para criar um evento");
        }
        event.setPrettyName(event.getTitle()
                .toLowerCase()
                .replaceAll(" ", "-"));
        return ResponseEntity.status(200).body(repository.save(event));
    }

    public List<Event> getAllEvents(){
        return (List<Event>) repository.findAll();
    }

    public Event getByPrettyName(String prettyName){
        return repository.findByPrettyName(prettyName);
    }

}
