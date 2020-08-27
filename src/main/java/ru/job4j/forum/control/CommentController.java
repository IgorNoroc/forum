package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class CommentController {
    private final PostService service;

    public CommentController(PostService service) {
        this.service = service;
    }

    @GetMapping("/comment")
    public String comment(@RequestParam("id") int id, Model model) {
        Post post = service.getPost(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "post";
    }

    @GetMapping("/addComment")
    public String addComment(@RequestParam("id") int id, Model model) {
        model.addAttribute("postId", id);
        return "add_comment";
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam("id") int id, @RequestParam("comment") String comment) {
        Post post = service.getPost(id);
        post.getComments().add(comment);
        service.savePost(post);
        return "redirect:/comment?id=" + post.getId();
    }
}
