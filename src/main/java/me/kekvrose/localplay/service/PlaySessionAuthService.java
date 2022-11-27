package me.kekvrose.localplay.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.kekvrose.localplay.dao.PlaySessionUserRepository;
import me.kekvrose.localplay.entity.PlaySessionUser;
import me.kekvrose.localplay.utils.Constants.Roles;

@Service
public class PlaySessionAuthService {

    @Autowired
    private PlaySessionUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public PlaySessionUser register(String username, String password) throws IllegalArgumentException {
        Optional<PlaySessionUser> playSessionUserOptional = userRepository.findByUsername(username);
        if (playSessionUserOptional.isPresent()) {
            throw new IllegalArgumentException("User exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        PlaySessionUser playSessionUser = new PlaySessionUser(null, username, encodedPassword, true,
                Collections.singletonList(Roles.USER_ROLE));
        return userRepository.save(playSessionUser);
    }
}
