package com.experiment.game.domain.model.game;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class Game {

    private String id;
    private String player1;
    private String player2;
    private String nextPlayer;
    private Integer result;
    private String winnerPlayer;
    private Status status;

    public Game(String player1, String player2) {
        this.id = UUID.randomUUID().toString();
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        status = Status.STARTED;
        nextPlayer = player1;
    }

    public void play(String playerId, Integer move) {
        playMove(move);
        changeTurn(playerId);
    }

    public void setAsFinished(String playerId) {
        status = Status.FINISHED;
        winnerPlayer = playerId;
    }

    public String getId() {
        return id;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    public Integer getResult() {
        return result;
    }

    public String getWinnerPlayer() {
        return winnerPlayer;
    }

    public Status getStatus() {
        return status;
    }

    private void playMove(Integer move) {
        if (move % 3 == 0) {
            result = move / 3;
        } else if (move % 3 == 1) {
            result = (move - 1) / 3;
        }

        result = (move + 1) / 3;
    }

    private void changeTurn(String playerId) {
        if (playerId.equals(player1)) {
            nextPlayer = player2;
            return;
        }

        nextPlayer = player1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Game game = (Game) o;

        return new EqualsBuilder()
                .append(id, game.id)
                .append(player1, game.player1)
                .append(player2, game.player2)
                .append(nextPlayer, game.nextPlayer)
                .append(result, game.result)
                .append(winnerPlayer, game.winnerPlayer)
                .append(status, game.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(player1)
                .append(player2)
                .append(nextPlayer)
                .append(result)
                .append(winnerPlayer)
                .append(status)
                .toHashCode();
    }
}
