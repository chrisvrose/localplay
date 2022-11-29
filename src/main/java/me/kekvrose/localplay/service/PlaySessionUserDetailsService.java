package me.kekvrose.localplay.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import me.kekvrose.localplay.dao.PlaySessionUserRepository;
import me.kekvrose.localplay.dto.PlaySessionUserDetails;
import me.kekvrose.localplay.entity.PlaySessionUser;
import me.kekvrose.localplay.utils.Constants.Roles;

@Service
public class PlaySessionUserDetailsService implements UserDetailsManager {

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

    @Override
    public void createUser(UserDetails user) {
        Optional<PlaySessionUser> playSessionUserOptional = userRepository.findByUsername(user.getUsername());
        if (playSessionUserOptional.isPresent()) {
            throw new IllegalArgumentException("User exists");
        }
        String username = user.getUsername();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        PlaySessionUser playSessionUser = new PlaySessionUser(null, username, encodedPassword, true,
                Collections.singletonList(Roles.USER_ROLE));
        userRepository.save(playSessionUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        // TODO Auto-generated method stub
        throw new IllegalAccessError("Not yet implemented");
    }

    @Override
    public void deleteUser(String username) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean userExists(String username) {
        // TODO Auto-generated method stub
        return false;
    }

    

}
