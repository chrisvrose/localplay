package me.kekvrose.localplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import me.kekvrose.localplay.entity.PlaySession;
import me.kekvrose.localplay.service.PlaySessionService;

@RestController
@AllArgsConstructor
@RequestMapping("/play")
public class PlaySessionController {
    @Autowired
    private PlaySessionService playSessionService;

    @PostMapping("")
    public PlaySession createSession(@AuthenticationPrincipal UserDetails user) {

        return playSessionService.create(user);

    }
}
