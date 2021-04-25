package com.experiment.game.domain.repository;

import com.experiment.game.domain.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameEligibleRepositoryTest {

    GameEligibleRepository repository;

    @Before
    public void setup() {
        repository = new GameEligibleRepository();
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
    public void it_should_get_two_player() {
        // Given
        Player player1 = new Player("player-one");
        Player player2 = new Player("player-two");
        Player player3 = new Player("player-three");

        repository.save(player1);
        repository.save(player2);
        repository.save(player3);

        // When
        List<Player> players = repository.getPlayers();

        // Then
        assertThat(players).hasSize(2);
    }

    @Test
    public void it_should_return_true_if_enough_players_on_the_game() {
        // Given
        Player player1 = new Player("player-one");
        Player player2 = new Player("player-two");

        repository.save(player1);
        repository.save(player2);

        // When
        boolean isEligibleToStart = repository.isEligibleToStart();

        // Then
        assertThat(isEligibleToStart).isTrue();
    }

    @Test
    public void it_should_return_false_if_enough_players_not_on_the_game() {
        // Given
        Player player1 = new Player("player-one");

        repository.save(player1);

        // When
        boolean isEligibleToStart = repository.isEligibleToStart();

        // Then
        assertThat(isEligibleToStart).isFalse();
    }
}