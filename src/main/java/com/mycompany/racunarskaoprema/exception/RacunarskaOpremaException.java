package com.mycompany.racunarskaoprema.exception;

public class RacunarskaOpremaException extends Exception {
    public RacunarskaOpremaException(String message) {
        super(message);
    }

    public RacunarskaOpremaException(String message, Throwable cause) {
        super(message, cause);
    }
}