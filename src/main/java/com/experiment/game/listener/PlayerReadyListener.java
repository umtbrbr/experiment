package com.experiment.game.listener;

import com.experiment.game.domain.model.event.PlayerReadyEvent;
import com.experiment.game.domain.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PlayerReadyListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerReadyListener.class);

    private final GameService gameService;

    public PlayerReadyListener(GameService gameService) {
        this.gameService = gameService;
    }

    @EventListener
    public void listen(PlayerReadyEvent event) {
        LOGGER.info("PlayerReadyEvent is listening message: {}", event);

        try {
            gameService.onPlayerReady(event);
        } catch (Exception e) {
            LOGGER.error("PlayerReadyEvent failed to listen because of unexpected error! Error: ", e);
            throw e;
        }
    }
}
