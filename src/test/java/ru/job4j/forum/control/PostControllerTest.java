package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControllerTest {
    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnSaveMessage() throws Exception {
        this.mockMvc.perform(get("/save"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("save"));
    }

    @Test
    @WithMockUser
    public void shouldSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Новая тема"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).savePost(argument.capture());
        assertThat(argument.getValue().getName(), is("Новая тема"));
    }

    @Test
    @WithMockUser
    public void whenEditMappingMethodGet() throws Exception {
        this.mockMvc.perform(get("/edit")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void whenEditUserMethodPost() throws Exception {
        this.mockMvc.perform(post("/edit")
                .param("name", "newIgor")
                .param("desc", "type1")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(posts).editPost(1, "newIgor", "type1");
    }
}