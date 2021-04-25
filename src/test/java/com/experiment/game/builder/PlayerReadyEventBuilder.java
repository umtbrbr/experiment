package com.experiment.game.builder;

import com.experiment.game.domain.model.event.PlayerReadyEvent;

public final class PlayerReadyEventBuilder {
    private String eventId;
    private Long timestamp;
    private String playerId;

    private PlayerReadyEventBuilder() {
    }

    public static PlayerReadyEventBuilder aPlayerReadyEvent() {
        return new PlayerReadyEventBuilder();
    }

    public PlayerReadyEventBuilder eventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public PlayerReadyEventBuilder timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PlayerReadyEventBuilder playerId(String playerId) {
        this.playerId = playerId;
        return this;
    }

    public PlayerReadyEvent build() {
        PlayerReadyEvent playerReadyEvent = new PlayerReadyEvent(playerId);
        playerReadyEvent.setEventId(eventId);
        playerReadyEvent.setTimestamp(timestamp);
        return playerReadyEvent;
    }
}
