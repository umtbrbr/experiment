package com.experiment.game.domain.repository;

import com.experiment.game.domain.model.player.Player;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class GameEligibleRepository {

    private static Set<Player> players = Collections.synchronizedSet(new HashSet<>());

    public void save(Player player) {
        players.add(player);
    }

    public Optional<Player> findById(String playerId) {
        return players.stream()
                .filter(f -> f.getId().equals(playerId))
                .findFirst();
    }

    public boolean isEligibleToStart() {
        return players.size() >= 2;
    }

    public List<Player> getPlayers() {
        return players.stream()
                .limit(2)
                .collect(Collectors.toList());
    }
}
