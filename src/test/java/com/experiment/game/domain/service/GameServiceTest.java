package com.experiment.game.domain.service;

import com.experiment.game.builder.PlayEventBuilder;
import com.experiment.game.builder.PlayerJoinEventBuilder;
import com.experiment.game.builder.PlayerReadyEventBuilder;
import com.experiment.game.domain.model.event.GameFinishedEvent;
import com.experiment.game.domain.model.event.PlayEvent;
import com.experiment.game.domain.model.event.PlayerJoinEvent;
import com.experiment.game.domain.model.event.PlayerMoveEvent;
import com.experiment.game.domain.model.event.PlayerReadyEvent;
import com.experiment.game.domain.model.game.Game;
import com.experiment.game.domain.model.game.Status;
import com.experiment.game.domain.model.player.Player;
import com.experiment.game.domain.repository.GameRepository;
import com.experiment.game.domain.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameEligibleService gameEligibleService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void it_should_join_player() {
        // Given
        PlayerJoinEvent event = PlayerJoinEventBuilder.aPlayerJoinEvent()
                .playerId("player-one")
                .build();

        // When
        gameService.onPlayerJoined(event);

        // Then
        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(playerArgumentCaptor.capture());

        assertThat(playerArgumentCaptor.getValue().getId()).isEqualTo("player-one");
    }

    @Test
    public void it_should_start_game() {
        // Given
        PlayerReadyEvent event = PlayerReadyEventBuilder.aPlayerReadyEvent()
                .playerId("player-one")
                .build();

        Player player1 = new Player("player-one");
        Player player2 = new Player("player-one");

        given(playerRepository.findById("player-one")).willReturn(Optional.of(player1));
        given(gameEligibleService.isEligibleToStart()).willReturn(true);
        given(gameEligibleService.getPlayers()).willReturn(Arrays.asList(player1, player2));

        // When
        gameService.onPlayerReady(event);

        // Then
        ArgumentCaptor<Game> gameArgumentCaptor = ArgumentCaptor.forClass(Game.class);
        verify(gameRepository).save(gameArgumentCaptor.capture());

        Game capturedGame = gameArgumentCaptor.getValue();
        assertThat(capturedGame.getStatus()).isEqualTo(Status.STARTED);
        assertThat(capturedGame.getNextPlayer()).isEqualTo("player-one");

        ArgumentCaptor<PlayerMoveEvent> moveEventArgumentCaptor = ArgumentCaptor.forClass(PlayerMoveEvent.class);
        verify(eventPublisher).publishEvent(moveEventArgumentCaptor.capture());

        PlayerMoveEvent capturedEvent = moveEventArgumentCaptor.getValue();
        assertThat(capturedEvent.getGameId()).isEqualTo(capturedGame.getId());
        assertThat(capturedEvent.getPlayerId()).isEqualTo("player-one");
    }

    @Test
    public void it_should_player_move_and_publish_player_move_event() {
        // Given
        PlayEvent event = PlayEventBuilder.aPlayEvent()
                .playerId("player-one")
                .gameId("game")
                .move(56)
                .build();

        Game game = new Game("player-one", "player-two");

        given(gameRepository.findById("game")).willReturn(Optional.of(game));

        // When
        gameService.onPlayerMoved(event);

        // Then
        assertThat(game.getNextPlayer()).isEqualTo("player-two");
        assertThat(game.getResult()).isEqualTo(19);

        ArgumentCaptor<PlayerMoveEvent> moveEventArgumentCaptor = ArgumentCaptor.forClass(PlayerMoveEvent.class);
        verify(eventPublisher).publishEvent(moveEventArgumentCaptor.capture());

        PlayerMoveEvent capturedEvent = moveEventArgumentCaptor.getValue();
        assertThat(capturedEvent.getGameId()).isEqualTo(game.getId());
        assertThat(capturedEvent.getPlayerId()).isEqualTo("player-two");
        assertThat(capturedEvent.getMove()).isEqualTo(19);
    }

    @Test
    public void it_should_player_move_and_publish_game_finished_event() {
        // Given
        PlayEvent event = PlayEventBuilder.aPlayEvent()
                .playerId("player-one")
                .gameId("game")
                .move(1)
                .build();

        Game game = new Game("player-one", "player-two");

        given(gameRepository.findById("game")).willReturn(Optional.of(game));

        // When
        gameService.onPlayerMoved(event);

        // Then
        assertThat(game.getStatus()).isEqualTo(Status.FINISHED);
        assertThat(game.getWinnerPlayer()).isEqualTo("player-one");

        ArgumentCaptor<GameFinishedEvent> gameFinishedEventArgumentCaptor = ArgumentCaptor.forClass(GameFinishedEvent.class);
        verify(eventPublisher).publishEvent(gameFinishedEventArgumentCaptor.capture());

        GameFinishedEvent capturedEvent = gameFinishedEventArgumentCaptor.getValue();
        assertThat(capturedEvent.getGameId()).isEqualTo(game.getId());
        assertThat(capturedEvent.getWinner()).isEqualTo("player-one");
    }
}