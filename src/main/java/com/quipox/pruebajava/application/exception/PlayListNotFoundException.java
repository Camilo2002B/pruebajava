package com.quipox.pruebajava.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "PlayList not found")
public class PlayListNotFoundException extends RuntimeException {
    public PlayListNotFoundException(String message) {
        super(message);
    }
}
