package me.kekvrose.localplay.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An entry for a playSession in database. Why exist? Because we can peer based
 * on this.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PlaySession {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime updateTime;

    @Embedded
    private PlaySessionDetails playSessionDetails;

    @ManyToOne(optional = false)
    private PlaySessionUser host;
}
