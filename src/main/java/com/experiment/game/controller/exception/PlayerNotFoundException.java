package com.experiment.game.controller.exception;

public class PlayerNotFoundException extends BaseException {
    public PlayerNotFoundException(String key, String... args) {
        super(key, args);
    }
}
