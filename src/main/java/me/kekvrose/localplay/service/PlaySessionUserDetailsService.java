package me.kekvrose.localplay.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.kekvrose.localplay.dao.PlaySessionUserRepository;
import me.kekvrose.localplay.dto.PlaySessionUserDetails;
import me.kekvrose.localplay.entity.PlaySessionUser;

@Service
public class PlaySessionUserDetailsService implements UserDetailsService {

    private final PlaySessionUserRepository userRepository;

    @Autowired
    public PlaySessionUserDetailsService(PlaySessionUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PlaySessionUser> playSessionUserOptional = userRepository.findByUsername(username);
        return playSessionUserOptional.map(PlaySessionUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
