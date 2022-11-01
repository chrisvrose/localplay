package me.kekvrose.localplay.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kekvrose.localplay.entity.PlaySession;

public interface PlaySessionRepository extends JpaRepository<PlaySession,Integer>{
    List<PlaySession> findByUpdateTimeBefore(LocalDateTime threshold);
    Optional<PlaySession> findByParticipantId(UUID participantId);
    Optional<PlaySession> findByHostId(UUID participantId);
}
