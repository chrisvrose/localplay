package me.kekvrose.localplay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import me.kekvrose.localplay.dto.ActionMessage;
import me.kekvrose.localplay.dto.ActionState;

@Controller
public class PlaySessionWSController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaySessionWSController.class);
    @MessageMapping("/session/{roomId}")
    @SendTo("/session/{roomId}")
    public ActionMessage session(@DestinationVariable("roomId") Integer roomId,String content) {
        LOGGER.info("Got message at room {} with content {}",roomId,content);
        return new ActionMessage(ActionState.ERR,416,"Hello World");
    }
}
