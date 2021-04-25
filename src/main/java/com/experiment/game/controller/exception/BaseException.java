package com.experiment.game.controller.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -8508000268865474432L;

    private final String key;
    private final String[] args;

    public BaseException(String key, String... args) {
        this.key = key;
        this.args = args != null ? args : ArrayUtils.EMPTY_STRING_ARRAY;
    }

    public String getKey() {
        return this.key;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String getMessage() {
        return "Business exception occurred " + key + " " + StringUtils.join(args, ", ");
    }
}
