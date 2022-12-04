package me.kekvrose.localplay.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.kekvrose.localplay.dao.PlaySessionUserRepository;
import me.kekvrose.localplay.dto.PlaySessionUserDetails;
import me.kekvrose.localplay.entity.PlaySessionUser;
import me.kekvrose.localplay.utils.Constants;

/**
 * Provides authorization functionality for Spring and userpass management
 */
@Service
public class PlaySessionUserDetailsService implements UserDetailsService {

    private final PlaySessionUserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public PlaySessionUserDetailsService(PlaySessionUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PlaySessionUser> playSessionUserOptional = userRepository.findByUsername(username);
        return playSessionUserOptional.map(PlaySessionUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    /**
     * Create an account
     * @param username Username
     * @param password Plaintext password
     */
    public void createUser(String username, String password) {
        Optional<PlaySessionUser> playSessionUserOptional = userRepository.findByUsername(username);
        if (playSessionUserOptional.isPresent()) {
            throw new IllegalArgumentException("User exists");
        }
        
        boolean isEnabled = true;
        String encodedPassword = passwordEncoder.encode(password);
        PlaySessionUser playSessionUser = new PlaySessionUser(null, username, encodedPassword, isEnabled,
                Constants.Roles.DEFAULT_ROLE_LIST);
        userRepository.save(playSessionUser);
    }
}
