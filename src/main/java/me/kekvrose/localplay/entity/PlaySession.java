package me.kekvrose.localplay.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;
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

    @NonNull
    private LocalDateTime updateTime;

    @ManyToOne(optional = false)
    private PlaySessionUser host;
}
