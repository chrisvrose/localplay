package me.kekvrose.localplay.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An entry for a playSession in database. Why exist? Because we can peer based
 * on this.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(columnList = "hostId"),
        @Index(columnList = "participantId"),
})
public class PlaySession {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NonNull
    private LocalDateTime updateTime;

    @NonNull
    @Column(nullable = false, unique = true)
    private UUID hostId;
    @NonNull
    private UUID participantId;

}
