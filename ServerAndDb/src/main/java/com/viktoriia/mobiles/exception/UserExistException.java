package com.viktoriia.mobiles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class UserExistException extends RuntimeException{
    public UserExistException (String exception) {
        super(exception);
    }
}
