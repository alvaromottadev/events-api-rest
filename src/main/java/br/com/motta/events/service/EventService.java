package br.com.motta.events.service;

import br.com.motta.events.model.Event;
import br.com.motta.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public Event addNewEvent(Event event){
        event.setPrettyName(event.getTitle()
                .toLowerCase()
                .replaceAll(" ", "-"));
        return repository.save(event);
    }

    public List<Event> getAllEvents(){
        return (List<Event>) repository.findAll();
    }

    public Event getByPrettyName(String prettyName){
        return repository.findByPrettyName(prettyName);
    }

}