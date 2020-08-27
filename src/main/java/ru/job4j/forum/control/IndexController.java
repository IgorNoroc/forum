package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.service.PostService;

@Controller
public class IndexController {
    private final PostService service;

    public IndexController(PostService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "log";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {

        boolean login = service.login(email, password);
        String auth = login ? "" : "ошибка при вводе почты или пароля";
        model.addAttribute("auth", auth);
        return login ? "redirect:/index" : "log";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("posts", service.allPosts());
        return "index";
    }
}
