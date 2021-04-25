package com.experiment.game.domain.repository;

import com.experiment.game.domain.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerRepositoryTest {

    PlayerRepository repository;

    @Before
    public void setup() {
        repository = new PlayerRepository();
    }

    @Test
    public void it_should_save_player() {
        // Given
        Player player = new Player("player-one");

        // When
        repository.save(player);

        // Then
        Player savedPlayer = repository.findById("player-one").get();

        assertThat(savedPlayer.getId()).isEqualTo(player.getId());
    }

    @Test
    public void it_should_find_player_by_id() {
        // Given
        Player player = new Player("player-one");

        repository.save(player);

        // When
        Player savedPlayer = repository.findById(player.getId()).get();

        // Then
        assertThat(savedPlayer.getId()).isEqualTo(player.getId());
    }
}