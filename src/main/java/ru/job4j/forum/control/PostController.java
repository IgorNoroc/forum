package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Date;

@Controller
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        post.setCreated(new Date(System.currentTimeMillis()));
        service.savePost(post);
        return "redirect:/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("currentPost", service.getPost(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("name") String name,
                       @RequestParam("desc") String desc,
                       @RequestParam("id") int id) {
       service.editPost(id, name, desc);
        return "redirect:/index";
    }
}
