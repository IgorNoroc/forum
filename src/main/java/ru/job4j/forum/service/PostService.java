package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repositories.CommentRepository;
import ru.job4j.forum.repositories.PostRepository;
import ru.job4j.forum.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository posts;
    private final UserRepository users;
    private final CommentRepository comments;

    public PostService(PostRepository posts, UserRepository users, CommentRepository comments) {
        this.posts = posts;
        this.users = users;
        this.comments = comments;
    }

    public User findUserByEmail(String email) {
        return users.findByEmail(email);
    }

    public Post getPost(int id) {
        return posts.findById(id).get();
    }

    public void savePost(Post post) {
        posts.save(post);
    }

    public void saveUser(User user) {
        users.save(user);
    }

    public void saveComment(Comment comment) {
        comments.save(comment);
    }

    public boolean login(String email, String password) {
        User user = findUserByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public List<Post> allPosts() {
        List<Post> rsl = new ArrayList<>();
        posts.findAll().forEach(rsl::add);
        return rsl;
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
            post.setDescription(description);
        }
        savePost(post);
    }
}
