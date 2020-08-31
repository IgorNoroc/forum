package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repositories.AuthorityRepository;
import ru.job4j.forum.service.PostService;

import java.sql.SQLException;

@Controller
public class RegController {
    private final PostService service;
    private final AuthorityRepository authorities;
    private final PasswordEncoder encoder;

    public RegController(PostService service, AuthorityRepository authorities, PasswordEncoder encoder) {
        this.service = service;
        this.authorities = authorities;
        this.encoder = encoder;
    }

    @GetMapping("/reg")
    public String registration() {
        return "reg";
    }

    @PostMapping("/reg")
    public String registration(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        try {
            service.saveUser(user);
        } catch (SQLException e) {
            String errorMessage = "! пользователь с таким именем уже существует";
            model.addAttribute("errorReg", errorMessage);
            return "/reg";
        }
        return "redirect:/login";
    }
}
