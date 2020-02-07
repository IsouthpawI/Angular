package com.viktoriia.mobiles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentInDataBase extends RuntimeException {
    public NoContentInDataBase(String exception){
        super(exception);
    }
}
