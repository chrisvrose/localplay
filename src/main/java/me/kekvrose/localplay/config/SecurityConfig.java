package me.kekvrose.localplay.config;

import static me.kekvrose.localplay.utils.Constants.Roles.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import me.kekvrose.localplay.service.PlaySessionUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,PlaySessionUserDetailsService playSessionUserDetailsService,PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(playSessionUserDetailsService).passwordEncoder(passwordEncoder);
        http
                .csrf().disable()
                .cors().disable()
                
                .formLogin().and()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/play/**").hasRole(USER_ROLE)
                .antMatchers("/user/**").hasRole(USER_ROLE)
                .antMatchers("/admin/**").hasRole(ADMIN_ROLE);
        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService(PlaySessionUserDetailsService playSessionUserDetailsService) {
        return playSessionUserDetailsService;
    }

    
}
