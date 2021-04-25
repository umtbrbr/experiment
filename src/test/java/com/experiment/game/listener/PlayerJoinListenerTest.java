package com.experiment.game.listener;

import com.experiment.game.builder.PlayerJoinEventBuilder;
import com.experiment.game.domain.model.event.PlayerJoinEvent;
import com.experiment.game.domain.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PlayerJoinListenerTest {

    @InjectMocks
    private PlayerJoinListener playerJoinListener;

    @Mock
    private GameService gameService;

    @Test
    public void it_should_listen_player_join_event() {
        // Given
        PlayerJoinEvent event = PlayerJoinEventBuilder.aPlayerJoinEvent()
                .playerId("player-one")
                .build();

        // When
        playerJoinListener.listen(event);

        // Then
        verify(gameService).onPlayerJoined(event);
    }

    @Test
    public void it_should_throw_exception() {
        // Given
        PlayerJoinEvent event = PlayerJoinEventBuilder.aPlayerJoinEvent()
                .playerId("player-one")
                .build();

        doThrow(RuntimeException.class).when(gameService).onPlayerJoined(event);

        // When
        Throwable throwable = catchThrowable(() -> playerJoinListener.listen(event));

        // Then
        assertThat(throwable).isNotNull();
    }
}