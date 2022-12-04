package me.kekvrose.localplay.dto.session;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import me.kekvrose.localplay.dto.user.PlaySessionUserDTO;
import me.kekvrose.localplay.entity.PlaySession;
import me.kekvrose.localplay.entity.PlaySessionDetails;

@AllArgsConstructor
public class PlaySessionDTO {
    PlaySession playSession;

    public Integer getId(){
        return playSession.getId();
    }
    public LocalDateTime getUpdateTime(){
        return playSession.getUpdateTime();
    }
    public PlaySessionDetails getPlaySessionDetails(){
        return playSession.getPlaySessionDetails();
    }
    public PlaySessionUserDTO getHost(){
        return new PlaySessionUserDTO(playSession.getHost());
    }
}
