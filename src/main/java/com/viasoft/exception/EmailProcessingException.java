package com.viasoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailProcessingException extends RuntimeException {
    public EmailProcessingException(String message) {
        super(message);
    }
}
