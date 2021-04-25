package com.experiment.game.domain.service;

import com.experiment.game.controller.exception.GameNotFoundException;
import com.experiment.game.controller.exception.PlayerNotFoundException;
import com.experiment.game.domain.model.event.GameFinishedEvent;
import com.experiment.game.domain.model.event.PlayEvent;
import com.experiment.game.domain.model.event.PlayerJoinEvent;
import com.experiment.game.domain.model.event.PlayerMoveEvent;
import com.experiment.game.domain.model.event.PlayerReadyEvent;
import com.experiment.game.domain.model.game.Game;
import com.experiment.game.domain.model.player.Player;
import com.experiment.game.domain.repository.GameRepository;
import com.experiment.game.domain.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final GameEligibleService gameEligibleService;
    private final ApplicationEventPublisher eventPublisher;

    public GameService(PlayerRepository playerRepository,
                       GameRepository gameRepository,
                       GameEligibleService gameEligibleService,
                       ApplicationEventPublisher eventPublisher) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.gameEligibleService = gameEligibleService;
        this.eventPublisher = eventPublisher;
    }

    public void onPlayerJoined(PlayerJoinEvent event) {
        playerRepository.save(new Player(event.getPlayerId()));
        LOGGER.info("Player {} joined the game", event.getPlayerId());
    }

    @Transactional
    public void onPlayerReady(PlayerReadyEvent event) {
        if (!playerRepository.findById(event.getPlayerId()).isPresent()) {
            throw new PlayerNotFoundException("Player not found. playerId: ", event.getPlayerId());
        }

        String playerId = event.getPlayerId();
        gameEligibleService.save(new Player(playerId));

        if (!gameEligibleService.isEligibleToStart()) {
            LOGGER.info("Waiting another player to join to start game");
            return;
        }

        List<Player> players = gameEligibleService.getPlayers();
        Game game = new Game(players.get(0).getId(), players.get(1).getId());
        game.start();
        gameRepository.save(game);

        eventPublisher.publishEvent(new PlayerMoveEvent(game.getId(), game.getNextPlayer(), game.getResult()));

        LOGGER.info("The game is started. Waiting player1 to make a move. GameId: {}", game.getId());
    }

    public void onPlayerMoved(PlayEvent event) {
        Game game = gameRepository.findById(event.getGameId())
                .orElseThrow(() -> new GameNotFoundException("Game not found. gameId: ", event.getGameId()));

        if (event.getMove() == 1) {
            game.setAsFinished(event.getPlayerId());
            eventPublisher.publishEvent(new GameFinishedEvent(game.getId(), game.getWinnerPlayer()));
            return;
        }

        game.play(event.getPlayerId(), event.getMove());

        eventPublisher.publishEvent(new PlayerMoveEvent(game.getId(), game.getNextPlayer(), game.getResult()));
    }
}
