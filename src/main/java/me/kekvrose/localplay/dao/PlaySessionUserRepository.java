package me.kekvrose.localplay.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kekvrose.localplay.entity.PlaySessionUser;

public interface PlaySessionUserRepository extends JpaRepository<PlaySessionUser,Integer>{
    Optional<PlaySessionUser> findByUsername(String username);
    PlaySessionUser getByUsername(String username);
}
