package com.experiment.game.domain.repository;

import com.experiment.game.domain.model.game.Game;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRepositoryTest {

    GameRepository repository;

    @Before
    public void setup() {
        repository = new GameRepository();
    }

    @Test
    public void it_should_save_game() {
        // Given
        Game game = new Game("player-one", "player-two");

        // When
        repository.save(game);

        // Then
        Game savedGame = repository.findById(game.getId()).get();

        assertThat(savedGame.getId()).isEqualTo(game.getId());
    }

    @Test
    public void it_should_find_game_by_id() {
        // Given
        Game game = new Game("player-one", "player-two");

        repository.save(game);

        // When
        Game savedGame = repository.findById(game.getId()).get();

        // Then
        assertThat(savedGame.getId()).isEqualTo(game.getId());
    }
}