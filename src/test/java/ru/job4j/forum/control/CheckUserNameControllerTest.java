package ru.job4j.forum.control;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.service.PostService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class CheckUserNameControllerTest {
    @MockBean
    private PostService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenCheckIfUserExistAlready() throws Exception {
        this.mockMvc.perform(post("/check")
                .param("name", "user"))
                .andDo(print())
                .andReturn();
        verify(service).findUserByName("user");
    }
}