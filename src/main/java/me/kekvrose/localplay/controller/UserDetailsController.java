package me.kekvrose.localplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.kekvrose.localplay.dto.auth.CredentialsBody;
import me.kekvrose.localplay.service.PlaySessionUserDetailsService;

@RestController
@RequestMapping("/register")
public class UserDetailsController {
    @Autowired
    PlaySessionUserDetailsService playSessionAuthService;

    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails UserDetails) {
        String username = UserDetails != null ? UserDetails.getUsername() : "<<unauthenticated>>";
        return "This is a restricted api, and you have logged in successfully! User:" + username;
    }

    @PostMapping("")
    public String register(@RequestBody CredentialsBody credentialsBody) {
        String username = credentialsBody.getUsername();
        String password = credentialsBody.getPassword();
        
        playSessionAuthService.createUser(username,password);
        return "done";
    }
}
