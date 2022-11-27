package me.kekvrose.localplay.dto;

import java.time.LocalDateTime;

import lombok.Data;
import me.kekvrose.localplay.entity.PlaySession;

@Data
public class PlaySessionShortDto {

    private Integer id;

    private LocalDateTime updateTime;

    private String username;


    public static PlaySessionShortDto fromPlaySession(PlaySession playSession) {
        PlaySessionShortDto dto = new PlaySessionShortDto();
        dto.setId(playSession.getId());
        dto.setUpdateTime(playSession.getUpdateTime());
        dto.setUsername(playSession.getHost().getUsername());
        return dto;
    }
    
}
