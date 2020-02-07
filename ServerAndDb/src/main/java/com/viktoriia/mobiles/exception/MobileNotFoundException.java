package com.viktoriia.mobiles.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//КОнкретное мое исключение которое я буду обрабатывать
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MobileNotFoundException extends RuntimeException{
    public MobileNotFoundException(String exception) {
        super(exception);
    }
}
