package com.experiment.game.domain.model.event;

import org.joda.time.DateTime;

import java.util.UUID;

public class PlayerMoveEvent {

    private String eventId;
    private Long timestamp;
    private String gameId;
    private String playerId;
    private Integer move;

    public PlayerMoveEvent(String gameId, String playerId, Integer move) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = DateTime.now().getMillis();
        this.gameId = gameId;
        this.playerId = playerId;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getMove() {
        return move;
    }

    public void setMove(Integer move) {
        this.move = move;
    }
}
