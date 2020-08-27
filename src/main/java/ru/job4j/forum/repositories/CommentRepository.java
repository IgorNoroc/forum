package ru.job4j.forum.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
