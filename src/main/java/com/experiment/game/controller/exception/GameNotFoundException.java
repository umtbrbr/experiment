package com.experiment.game.controller.exception;

public class GameNotFoundException extends BaseException {
    public GameNotFoundException(String key, String... args) {
        super(key, args);
    }
}
