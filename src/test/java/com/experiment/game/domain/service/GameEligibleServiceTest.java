package com.experiment.game.domain.service;

import com.experiment.game.domain.model.player.Player;
import com.experiment.game.domain.repository.GameEligibleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameEligibleServiceTest {

    @InjectMocks
    private GameEligibleService gameEligibleService;

    @Mock
    private GameEligibleRepository repository;

    @Test
    public void it_should_save_player() {
        // Given
        Player player = new Player("player-one");

        // When
        gameEligibleService.save(player);

        // Then
        verify(repository).save(player);
    }

    @Test
    public void it_should_get_two_player() {
        // Given
        Player player1 = new Player("player-one");
        Player player2 = new Player("player-two");

        given(repository.getPlayers()).willReturn(Arrays.asList(player1, player2));

        // When
        List<Player> players = gameEligibleService.getPlayers();

        // Then
        assertThat(players).hasSize(2);
    }

    @Test
    public void it_should_return_true_if_enough_players_on_the_game() {
        // Given

        given(repository.isEligibleToStart()).willReturn(true);

        // When
        boolean isEligibleToStart = gameEligibleService.isEligibleToStart();

        // Then
        assertThat(isEligibleToStart).isTrue();
    }
}