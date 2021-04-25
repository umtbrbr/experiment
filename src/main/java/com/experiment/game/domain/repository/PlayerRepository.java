package com.experiment.game.domain.repository;

import com.experiment.game.domain.model.player.Player;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class PlayerRepository {

    private static Set<Player> players = Collections.synchronizedSet(new HashSet<>());

    public Player save(Player player) {
        final Optional<Player> playerMaybe = players.stream()
                .filter(f -> f.getId().equals(player.getId()))
                .findFirst();

        if (!playerMaybe.isPresent()) {
            players.add(player);
        }

        return player;
    }

    public Optional<Player> findById(String playerId) {
        return players.stream()
                .filter(player -> player.getId().equals(playerId))
                .findFirst();
    }
}
