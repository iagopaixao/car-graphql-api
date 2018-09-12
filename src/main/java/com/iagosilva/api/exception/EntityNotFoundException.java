package com.iagosilva.api.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {}

    public EntityNotFoundException(String msg) {
        super(msg);
    }

    public EntityNotFoundException(String msg, Throwable throwable) { super(msg, throwable); }

    public EntityNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public EntityNotFoundException(String msg, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, throwable, enableSuppression, writableStackTrace);
    }
}
