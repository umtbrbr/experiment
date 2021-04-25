package com.experiment.game.listener;

import com.experiment.game.builder.PlayerReadyEventBuilder;
import com.experiment.game.domain.model.event.PlayerReadyEvent;
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
public class PlayerReadyListenerTest {

    @InjectMocks
    private PlayerReadyListener playerReadyListener;

    @Mock
    private GameService gameService;

    @Test
    public void it_should_listen_player_ready_event() {
        // Given
        PlayerReadyEvent event = PlayerReadyEventBuilder.aPlayerReadyEvent()
                .playerId("player-one")
                .build();

        // When
        playerReadyListener.listen(event);

        // Then
        verify(gameService).onPlayerReady(event);
    }

    @Test
    public void it_should_throw_exception() {
        // Given
        PlayerReadyEvent event = PlayerReadyEventBuilder.aPlayerReadyEvent()
                .playerId("player-one")
                .build();

        doThrow(RuntimeException.class).when(gameService).onPlayerReady(event);

        // When
        Throwable throwable = catchThrowable(() -> playerReadyListener.listen(event));

        // Then
        assertThat(throwable).isNotNull();
    }
}