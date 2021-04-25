package com.experiment.game.domain.repository;

import com.experiment.game.domain.model.game.Game;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class GameRepository {

    private Set<Game> games = new HashSet<>();

    public Optional<Game> findById(String id) {
        return games.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }

    public Game save(Game game) {
        final Optional<Game> gameMaybe = games.stream()
                .filter(f -> f.getId().equals(game.getId()))
                .findFirst();

        if (!gameMaybe.isPresent()) {
            games.add(game);
        }

        return game;
    }
}
