package com.experiment.game.domain.service;

import com.experiment.game.domain.model.player.Player;
import com.experiment.game.domain.repository.GameEligibleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameEligibleService {

    private final GameEligibleRepository gameEligibleRepository;

    public GameEligibleService(GameEligibleRepository gameEligibleRepository) {
        this.gameEligibleRepository = gameEligibleRepository;
    }

    public void save(Player player) {
        gameEligibleRepository.save(player);
    }

    public boolean isEligibleToStart() {
        return gameEligibleRepository.isEligibleToStart();
    }

    public List<Player> getPlayers() {
        return gameEligibleRepository.getPlayers();
    }
}
