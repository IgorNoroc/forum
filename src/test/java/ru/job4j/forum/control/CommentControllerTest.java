package ru.job4j.forum.control;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class CommentControllerTest {
    @MockBean
    private PostService service;
    @MockBean
    private Post post;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnPostPageAndMethodGetPost() throws Exception {
        when(service.getPost(1)).thenReturn(post);
        this.mockMvc.perform(get("/comment")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
        verify(service).getPost(1);
    }

    @Test
    @WithMockUser
    public void shouldReturnAddCommentPage() throws Exception {
        this.mockMvc.perform(get("/addComment")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("add_comment"));
    }

    @Test
    @WithMockUser
    public void shouldCallMethodGetPostAndSaveComment() throws Exception {
        Comment comment = new Comment("comment", post);
        when(service.getPost(1)).thenReturn(post);
        this.mockMvc.perform(post("/addComment")
                .param("id", "1")
                .param("comment", "comment"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(service).getPost(1);
        verify(service).saveComment(comment);
    }
}