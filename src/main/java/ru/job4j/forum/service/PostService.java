package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.MemRepository;

import java.util.Collection;

@Service
public class PostService {
    private final MemRepository repository;

    public PostService(MemRepository repository) {
        this.repository = repository;
    }

    public Collection<Post> allPosts() {
        return repository.getAll();
    }

    public boolean login(String email, String password) {
        User user = repository.findUSerByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public void saveUser(User user) {
        repository.addUser(user);
    }

    public void savePost(Post post) {
        repository.addPost(post);
    }

    public Post getPost(int id) {
        return repository.findPostById(id);
    }

    /**
     * Edit post.
     *
     * @param id          post id.
     * @param name        new name.
     * @param description new description.
     */
    public void editPost(int id, String name, String description) {
        Post post = getPost(id);
        if (!name.equals("")) {
            post.setName(name);
        }
        if (!description.equals("")) {
            post.setDesc(description);
        }
        savePost(post);
    }
}
