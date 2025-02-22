package br.com.motta.events.repository;

import br.com.motta.events.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {

    public Event findByPrettyName(String prettyName);

}
