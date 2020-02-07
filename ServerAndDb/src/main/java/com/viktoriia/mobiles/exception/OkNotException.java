package com.viktoriia.mobiles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class OkNotException extends RuntimeException {
    public OkNotException(String exception) {
        super(exception);
    }
}
