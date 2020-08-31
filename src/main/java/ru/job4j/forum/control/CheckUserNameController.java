package ru.job4j.forum.control;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

@Controller
public class CheckUserNameController {
    private final PostService service;

    public CheckUserNameController(PostService service) {
        this.service = service;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public @ResponseBody String check(@RequestParam(value = "name", required = false) String name)  {
        User user = service.findUserByName(name);
        return user != null ?  new Gson().toJson("find") : new Gson().toJson("not");
    }
}
