package me.kekvrose.localplay.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import me.kekvrose.localplay.dao.PlaySessionRepository;
import me.kekvrose.localplay.dao.PlaySessionUserRepository;
import me.kekvrose.localplay.dto.PlaySessionUserDetails;
import me.kekvrose.localplay.entity.PlaySession;
import me.kekvrose.localplay.entity.PlaySessionDetails;
import me.kekvrose.localplay.entity.PlaySessionUser;

@ExtendWith(MockitoExtension.class)
public class PlaySessionServiceTest {
    @Mock
    private PlaySessionRepository playSessionRepository;
    @Mock
    private PlaySessionUserRepository playSessionUserRepository;

    private Instant instant = Instant.now();

    private Clock clock = Clock.fixed(instant, ZoneId.systemDefault());

    private PlaySessionService playSessionService;

    @Mock
    private PlaySessionUserDetails playSessionUserDetails;
    @Mock
    private PlaySessionUser playSessionUser;

    @BeforeEach
    void setup() {
        
        playSessionService = new PlaySessionService(playSessionRepository, playSessionUserRepository, clock);
    }
    
    @Test
    public void createWorks(){
        doReturn("user").when(playSessionUserDetails).getUsername();
        doReturn(playSessionUser).when(playSessionUserRepository).getByUsername("user");

        when(playSessionRepository.save(any())).thenAnswer((invocation)->{
            PlaySession playSession = invocation.getArgument(0);
            playSession.setId(5);
            return playSession;
        });

        PlaySession playSession = playSessionService.create(playSessionUserDetails);

        verify(playSessionRepository).save(any());
        
        assertThat(playSession).isNotNull();
        assertThat(playSession.getId()).isNotNull().isEqualTo(5);
        assertThat(playSession.getHost()).isSameAs(playSessionUser);
    }

    @Test
    public void cleanupWorks() {
        List<PlaySession> playSessions = Arrays.asList(
                new PlaySession(1, LocalDateTime.now(clock).plusHours(-1),new PlaySessionDetails(), playSessionUser),
                new PlaySession(1, LocalDateTime.now(clock).plusDays(-1),new PlaySessionDetails(), playSessionUser));
        when(playSessionRepository.findByUpdateTimeBefore(any())).thenReturn(playSessions);

        List<PlaySession> returnedPlaySessions = playSessionService.cleanup();

        assertThat(returnedPlaySessions).isSameAs(playSessions)
                .allMatch(e -> e.getUpdateTime().isBefore(LocalDateTime.now(clock)));
        verify(playSessionRepository).deleteAll(returnedPlaySessions);
        verify(playSessionRepository).findByUpdateTimeBefore(LocalDateTime.now(clock));
    }
}
