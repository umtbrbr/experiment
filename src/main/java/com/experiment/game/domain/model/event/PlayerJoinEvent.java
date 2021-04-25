package com.experiment.game.domain.model.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import java.util.UUID;

public class PlayerJoinEvent {

    private String eventId;
    private Long timestamp;
    private String playerId;

    public PlayerJoinEvent(String playerId) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = DateTime.now().getMillis();
        this.playerId = playerId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("eventId", eventId)
                .append("timestamp", timestamp)
                .append("playerId", playerId)
                .toString();
    }
}
