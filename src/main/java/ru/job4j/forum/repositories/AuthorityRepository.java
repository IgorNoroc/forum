package ru.job4j.forum.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
