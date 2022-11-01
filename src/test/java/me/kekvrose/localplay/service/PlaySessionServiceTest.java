package me.kekvrose.localplay.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import me.kekvrose.localplay.dao.PlaySessionRepository;
import me.kekvrose.localplay.entity.PlaySession;

@ExtendWith(MockitoExtension.class)
public class PlaySessionServiceTest {
    @Mock
    private PlaySessionRepository playSessionRepository;

    private Instant instant = Instant.now();
    private Clock clock = Clock.fixed(instant, ZoneId.systemDefault());

    private PlaySessionService playSessionService;

    @BeforeEach
    void setup() {
        playSessionService = new PlaySessionService(playSessionRepository, clock);
    }

    @Test
    public void createWorks(){
        when(playSessionRepository.save(any())).thenAnswer((invocation)->{
            PlaySession playSession = invocation.getArgument(0);
            return new PlaySession(5,playSession.getUpdateTime(),UUID.randomUUID(),UUID.randomUUID());
        });

        PlaySession playSession = playSessionService.create();

        verify(playSessionRepository).save(any());
        assertThat(playSession).isNotNull();
        assertThat(playSession.getId()).isNotNull();
        assertThat(playSession.getHostId()).isNotNull();
        assertThat(playSession.getParticipantId()).isNotNull();
    }

    @Test
    public void cleanupWorks() {
        List<PlaySession> playSessions = Arrays.asList(
                new PlaySession(1, LocalDateTime.now(clock).plusHours(-1), UUID.randomUUID(), UUID.randomUUID()),
                new PlaySession(1, LocalDateTime.now(clock).plusDays(-1), UUID.randomUUID(), UUID.randomUUID()));
        when(playSessionRepository.findByUpdateTimeBefore(any())).thenReturn(playSessions);

        List<PlaySession> returnedPlaySessions = playSessionService.cleanup();

        assertThat(returnedPlaySessions).isSameAs(playSessions)
                .allMatch(e -> e.getUpdateTime().isBefore(LocalDateTime.now(clock)));
        verify(playSessionRepository).findByUpdateTimeBefore(LocalDateTime.now(clock));
    }
}
