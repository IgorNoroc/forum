package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemRepository {
    private final HashMap<String, User> users = new HashMap<>();
    private final HashMap<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(1);

    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public User findUSerByEmail(String email) {
        return users.get(email);
    }

    public void addPost(Post post) {
        if (post.getId() == 0) {
            post.setId(id.get());
            posts.put(id.getAndIncrement(), post);
        } else {
            posts.put(post.getId(), post);
        }
    }

    public Collection<Post> getAll() {
        return posts.values();
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }
}
