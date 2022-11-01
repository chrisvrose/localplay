package me.kekvrose.localplay.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.AllArgsConstructor;
import me.kekvrose.localplay.service.PlaySessionService;

/**
 * Scheduled cleanup job which runs periodically.
 */
@Configuration
@EnableScheduling
@AllArgsConstructor
public class PlaySessionScheduledJobs {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaySessionScheduledJobs.class);

    @Autowired
    PlaySessionService playSessionService;
    

    @Scheduled(fixedRate = 60*60*1_000)
    public void cleanup(){
        LOGGER.info("Cleaning up jobs");
        var elements = playSessionService.cleanup();
        LOGGER.info("Cleanup finished - cleaned {} sessions",elements.size());
    }
}
