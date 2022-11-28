package me.kekvrose.localplay.controller;

import static me.kekvrose.localplay.utils.Constants.Roles.USER_ROLE;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import me.kekvrose.localplay.entity.PlaySession;
import me.kekvrose.localplay.entity.PlaySessionDetails;
import me.kekvrose.localplay.entity.PlaySessionUser;
import me.kekvrose.localplay.service.PlaySessionService;

@WebMvcTest(PlaySessionController.class)
@WithMockUser(username = "admin", roles = { "USER", "ADMIN" }, password = "helloworld")
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
        PlaySession playSession = new PlaySession(1, LocalDateTime.now(),
                new PlaySessionDetails(),new PlaySessionUser(5, "admin", "password", true, Arrays.asList(USER_ROLE)));

        doReturn(playSession).when(playSessionService).create(any());

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/play").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}
