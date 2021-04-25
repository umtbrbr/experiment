package com.experiment.game.domain.model.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import java.util.UUID;

public class PlayEvent {

    private String eventId;
    private Long timestamp;
    private String playerId;
    private String gameId;
    private Integer move;

    public PlayEvent(String playerId, String gameId, Integer move) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = DateTime.now().getMillis();
        this.playerId = playerId;
        this.gameId = gameId;
        this.move = move;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Integer getMove() {
        return move;
    }

    public void setMove(Integer move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("eventId", eventId)
                .append("timestamp", timestamp)
                .append("playerId", playerId)
                .append("gameId", gameId)
                .append("move", move)
                .toString();
    }
}
