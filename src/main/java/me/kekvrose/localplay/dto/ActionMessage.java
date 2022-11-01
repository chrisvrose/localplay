package me.kekvrose.localplay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActionMessage {
    private final ActionState state;
    private final long timestamp;
    private final String comment;
}
