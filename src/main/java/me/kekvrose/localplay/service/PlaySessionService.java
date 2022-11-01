package me.kekvrose.localplay.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.kekvrose.localplay.dao.PlaySessionRepository;
import me.kekvrose.localplay.entity.PlaySession;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class PlaySessionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaySessionService.class);
    @Autowired
    private PlaySessionRepository playSessionRepository;
    @Autowired
    private Clock localClock;

    public PlaySession create() {
        PlaySession playSession = new PlaySession();
        playSession.setUpdateTime(
                LocalDateTime.now(localClock).plusDays(1));

        playSession.setHostId(UUID.randomUUID());
        playSession.setParticipantId(UUID.randomUUID());

        LOGGER.debug("Creating play session {}", playSession);
        return playSessionRepository.save(playSession);
    }

    @Transactional
    public List<PlaySession> cleanup() {
        List<PlaySession> playsessions = playSessionRepository.findByUpdateTimeBefore(LocalDateTime.now(localClock));
        LOGGER.debug("Found {} elements to cleanup", playsessions.size());
        playSessionRepository.deleteAll(playsessions);
        return playsessions;
    }
}
