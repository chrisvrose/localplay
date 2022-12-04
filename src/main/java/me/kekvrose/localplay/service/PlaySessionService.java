package me.kekvrose.localplay.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.kekvrose.localplay.dao.PlaySessionRepository;
import me.kekvrose.localplay.dao.PlaySessionUserRepository;
import me.kekvrose.localplay.dto.session.PlaySessionDTO;
import me.kekvrose.localplay.entity.PlaySession;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class PlaySessionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaySessionService.class);
    @Autowired
    private PlaySessionRepository playSessionRepository;

    @Autowired
    private PlaySessionUserRepository playSessionUserRepository;
    @Autowired
    private Clock localClock;

    @Transactional
    public PlaySessionDTO create(UserDetails userDetails) {
        PlaySession playSession = new PlaySession();
        playSession.setUpdateTime(
                LocalDateTime.now(localClock).plusDays(1));

        
        playSession.setHost(playSessionUserRepository.getByUsername(userDetails.getUsername()));
        LOGGER.debug("Creating play session {}", playSession);
        return new PlaySessionDTO(playSessionRepository.save(playSession));
    }

    @Transactional
    public List<PlaySessionDTO> cleanup() {
        List<PlaySession> playsessions = playSessionRepository.findByUpdateTimeBefore(LocalDateTime.now(localClock));
        LOGGER.debug("Found {} elements to cleanup", playsessions.size());
        playSessionRepository.deleteAll(playsessions);
        return playsessions.stream().map(e->new PlaySessionDTO(e)).toList();
    }
}
