package com.izyver.biplanes.engine.exceptions;

public class NotInitializedValue extends RuntimeException {
    public NotInitializedValue() {
        super();
    }

    public NotInitializedValue(String message) {
        super(message);
    }

    public NotInitializedValue(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInitializedValue(Throwable cause) {
        super(cause);
    }

    protected NotInitializedValue(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
