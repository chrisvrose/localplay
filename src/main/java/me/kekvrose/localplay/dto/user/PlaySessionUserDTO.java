package me.kekvrose.localplay.dto.user;

import lombok.AllArgsConstructor;
import me.kekvrose.localplay.entity.PlaySessionUser;

@AllArgsConstructor
public class PlaySessionUserDTO {
    PlaySessionUser playSessionUser;
    
    public String getUsername(){
        return playSessionUser.getUsername();
    }
}
