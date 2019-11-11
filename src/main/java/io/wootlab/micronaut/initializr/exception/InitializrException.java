package io.wootlab.micronaut.initializr.exception;

public class InitializrException extends Exception {
    private String initialType;

    public InitializrException(String message, String initialType, Throwable t) {
        super(message, t);
        this.initialType = initialType;
    }

    public InitializrException(String message, Throwable t) {
        super(message, t);
    }
}
