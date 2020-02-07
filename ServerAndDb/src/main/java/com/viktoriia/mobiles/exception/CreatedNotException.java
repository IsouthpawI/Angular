package com.viktoriia.mobiles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Нужно ли создавать для все хорошо???
@ResponseStatus(HttpStatus.CREATED)
public class CreatedNotException  extends RuntimeException {
    public CreatedNotException (String exception) {
        super(exception);
    }
}