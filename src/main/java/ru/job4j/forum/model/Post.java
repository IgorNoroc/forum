package ru.job4j.forum.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Post {
    private int id;
    private String name;
    private String desc;
    private Date created;
    private final Set<String> comments = new HashSet<>();

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }

    public Set<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(name, post.name) &&
                Objects.equals(desc, post.desc) &&
                Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", created=" + created +
                ", comments=" + comments +
                '}';
    }
}
