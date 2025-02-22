package br.com.motta.events.repository;

import br.com.motta.events.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);

}
