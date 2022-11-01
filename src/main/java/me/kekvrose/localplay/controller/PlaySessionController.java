package me.kekvrose.localplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.kekvrose.localplay.entity.PlaySession;
import me.kekvrose.localplay.service.PlaySessionService;

@RestController
@RequestMapping("/play")
public class PlaySessionController {
    @Autowired
    public PlaySessionService playSessionService;

    @PostMapping("")
    public PlaySession createSession() {
        return playSessionService.create();
    }

}
