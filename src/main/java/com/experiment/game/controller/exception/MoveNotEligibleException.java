package com.experiment.game.controller.exception;

public class MoveNotEligibleException extends BaseException {
    public MoveNotEligibleException(String key, String... args) {
        super(key, args);
    }
}
