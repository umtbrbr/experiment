package com.experiment.game.builder;

import com.experiment.game.domain.model.event.PlayerJoinEvent;

public final class PlayerJoinEventBuilder {
    private String eventId;
    private Long timestamp;
    private String playerId;

    private PlayerJoinEventBuilder() {
    }

    public static PlayerJoinEventBuilder aPlayerJoinEvent() {
        return new PlayerJoinEventBuilder();
    }

    public PlayerJoinEventBuilder eventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public PlayerJoinEventBuilder timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PlayerJoinEventBuilder playerId(String playerId) {
        this.playerId = playerId;
        return this;
    }

    public PlayerJoinEvent build() {
        PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(playerId);
        playerJoinEvent.setEventId(eventId);
        playerJoinEvent.setTimestamp(timestamp);
        return playerJoinEvent;
    }
}
