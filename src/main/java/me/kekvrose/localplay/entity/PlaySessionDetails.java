package me.kekvrose.localplay.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Embeddable
public class PlaySessionDetails {

    
    public String videoName;
    public String videoChecksum;

    @ElementCollection //(fetch = FetchType.EAGER)
    private List<String> subtitles;
}
