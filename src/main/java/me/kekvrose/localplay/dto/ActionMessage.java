package me.kekvrose.localplay.dto;

public class ActionMessage {
    private final ActionState state;
    private final long timestamp;
    private final String comment;

    public ActionMessage(ActionState state, long timestamp, String comment) {
        this.state = state;
        this.timestamp = timestamp;
        this.comment = comment;
    }
    
    public ActionState getState() {
        return state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getComment() {
        return comment;
    }    
}
