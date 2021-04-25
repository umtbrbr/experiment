package com.experiment.game.domain.model.event;

import org.joda.time.DateTime;

import java.util.UUID;

public class GameFinishedEvent {

    private String eventId;
    private Long timestamp;
    private String gameId;
    private String winner;

    public GameFinishedEvent(String gameId, String winner) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = DateTime.now().getMillis();
        this.gameId = gameId;
        this.winner = winner;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
