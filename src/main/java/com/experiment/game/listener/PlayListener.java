package com.experiment.game.listener;

import com.experiment.game.domain.model.event.PlayEvent;
import com.experiment.game.domain.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PlayListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayListener.class);

    private final GameService gameService;

    public PlayListener(GameService gameService) {
        this.gameService = gameService;
    }

    @EventListener
    public void listen(PlayEvent event) {
        LOGGER.info("PlayListener is listening message: {}", event);

        try {
            gameService.onPlayerMoved(event);
        } catch (Exception e) {
            LOGGER.error("PlayListener failed to listen because of unexpected error! Error: ", e);
            throw e;
        }
    }
}
