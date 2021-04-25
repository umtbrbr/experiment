package com.experiment.game.listener;

import com.experiment.game.domain.model.event.PlayerJoinEvent;
import com.experiment.game.domain.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PlayerJoinListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerJoinListener.class);

    private final GameService gameService;

    public PlayerJoinListener(GameService gameService) {
        this.gameService = gameService;
    }

    @EventListener
    public void listen(PlayerJoinEvent event) {
        LOGGER.info("PlayerJoinListener is listening message: {}", event);

        try {
            gameService.onPlayerJoined(event);
        } catch (Exception e) {
            LOGGER.error("PlayerJoinListener failed to listen because of unexpected error! Error: ", e);
            throw e;
        }
    }
}
