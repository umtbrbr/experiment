package com.experiment.game.listener;

import com.experiment.game.builder.PlayEventBuilder;
import com.experiment.game.domain.model.event.PlayEvent;
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
public class PlayListenerTest {

    @InjectMocks
    private PlayListener playListener;

    @Mock
    private GameService gameService;

    @Test
    public void it_should_listen_play_event() {
        // Given
        PlayEvent event = PlayEventBuilder.aPlayEvent()
                .playerId("player-one")
                .build();

        // When
        playListener.listen(event);

        // Then
        verify(gameService).onPlayerMoved(event);
    }

    @Test
    public void it_should_throw_exception() {
        // Given
        PlayEvent event = PlayEventBuilder.aPlayEvent()
                .playerId("player-one")
                .build();

        doThrow(RuntimeException.class).when(gameService).onPlayerMoved(event);

        // When
        Throwable throwable = catchThrowable(() -> playListener.listen(event));

        // Then
        assertThat(throwable).isNotNull();
    }

}