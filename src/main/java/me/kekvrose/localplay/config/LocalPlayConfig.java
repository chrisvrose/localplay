package me.kekvrose.localplay.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Provides miscellaneous configuration (beans).
 */
@Configuration
public class LocalPlayConfig {
    @Bean
    public Clock clock(){
        return Clock.systemUTC();
    }

    

}
