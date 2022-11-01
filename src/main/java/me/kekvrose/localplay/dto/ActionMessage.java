package me.kekvrose.localplay.dto;

import lombok.Getter;

@Getter
public class ActionMessage {
    private final ActionState state;
    private final long timestamp;
    private final String comment;

    public ActionMessage(ActionState state, long timestamp, String comment) {
        this.state = state;
        this.timestamp = timestamp;
        this.comment = comment;
    }
}
