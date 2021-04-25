package com.experiment.game.domain.model.game;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void it_should_start_game() {
        // Given
        Game game = new Game("player-one", "player-two");

        // When
        game.start();

        // Then
        assertThat(game.getStatus()).isEqualTo(Status.STARTED);
        assertThat(game.getNextPlayer()).isEqualTo("player-one");
    }

    @Test
    public void it_should_set_as_finished() {
        // Given
        Game game = new Game("player-one", "player-two");

        // When
        game.setAsFinished("player-two");

        // Then
        assertThat(game.getStatus()).isEqualTo(Status.FINISHED);
        assertThat(game.getWinnerPlayer()).isEqualTo("player-two");
    }

    @Test
    public void it_should_play() {
        // Given
        Game game = new Game("player-one", "player-two");

        // When
        game.play("player-one", 17);

        // Then
        assertThat(game.getResult()).isEqualTo(6);
        assertThat(game.getNextPlayer()).isEqualTo("player-two");
    }
}