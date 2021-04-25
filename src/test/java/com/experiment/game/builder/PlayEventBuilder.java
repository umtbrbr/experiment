package com.experiment.game.builder;

import com.experiment.game.domain.model.event.PlayEvent;

public final class PlayEventBuilder {
    private String eventId;
    private Long timestamp;
    private String playerId;
    private String gameId;
    private Integer move;

    private PlayEventBuilder() {
    }

    public static PlayEventBuilder aPlayEvent() {
        return new PlayEventBuilder();
    }

    public PlayEventBuilder eventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public PlayEventBuilder timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PlayEventBuilder playerId(String playerId) {
        this.playerId = playerId;
        return this;
    }

    public PlayEventBuilder gameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public PlayEventBuilder move(Integer move) {
        this.move = move;
        return this;
    }

    public PlayEvent build() {
        PlayEvent playEvent = new PlayEvent(playerId, gameId, move);
        playEvent.setEventId(eventId);
        playEvent.setTimestamp(timestamp);
        return playEvent;
    }
}
