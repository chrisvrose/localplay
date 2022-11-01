package me.kekvrose.localplay.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
