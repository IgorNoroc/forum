package ru.job4j.forum.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;


public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);

    User findByUsername(String username);
}
