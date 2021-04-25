package com.experiment.game.controller;

import com.experiment.game.domain.model.event.PlayerJoinEvent;
import com.experiment.game.domain.model.event.PlayEvent;
import com.experiment.game.domain.model.event.PlayerReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private ApplicationEventPublisher eventPublisher;

    public GameController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @MessageMapping("/join")
    public void join(SimpMessageHeaderAccessor headerAccessor) {
        String playerId = headerAccessor.getUser().getName();
        eventPublisher.publishEvent(new PlayerJoinEvent(playerId));
    }

    @MessageMapping("/ready")
    public void ready(SimpMessageHeaderAccessor headerAccessor) {
        String playerId = headerAccessor.getUser().getName();
        eventPublisher.publishEvent(new PlayerReadyEvent(playerId));
    }

    @MessageMapping("/play")
    public void play(Integer move, String gameId, SimpMessageHeaderAccessor headerAccessor) {
        String playerId = headerAccessor.getUser().getName();
        eventPublisher.publishEvent(new PlayEvent(playerId, gameId, move));
    }

}
