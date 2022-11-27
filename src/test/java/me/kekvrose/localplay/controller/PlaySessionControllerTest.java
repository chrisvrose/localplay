package me.kekvrose.localplay.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import me.kekvrose.localplay.entity.PlaySession;
import me.kekvrose.localplay.service.PlaySessionService;

@WebMvcTest(PlaySessionController.class)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class PlaySessionControllerTest {
    @MockBean
    private PlaySessionService playSessionService;


    @Autowired
    private PlaySessionController playSessionController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(playSessionController).isNotNull();
    }

    @Test
    void playSessionControllerGetWorks() throws Exception {
        PlaySession s = new PlaySession(1,LocalDateTime.now(),UUID.randomUUID(),UUID.randomUUID());

        doReturn(s).when(playSessionService).create();

        this.mockMvc
            .perform(MockMvcRequestBuilders.post("/play").with(csrf()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}
