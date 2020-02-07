package com.viktoriia.mobiles.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.http.HTTPException;
import java.util.Date;


//Глобальный гласс обработки исключений, он знает, что есть произошло такое-то исключение, то сделай мне код под этим исключением
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(ServerError.class)
//    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ServerError ex, WebRequest request){
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value() ,ex.getMessage(),
//                request.getDescription(false));
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(NoContentInDataBase.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(NoContentInDataBase ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NO_CONTENT.value() , ex.getMessage(),
                request.getDescription(false));
        //System.out.println( HttpStatus.NO_CONTENT.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(MobileNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(MobileNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                request.getDescription(false));
        System.out.println(errorDetails.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(BadRequestException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        System.out.println(errorDetails.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CreatedNotException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(CreatedNotException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.CREATED.value(), ex.getMessage(),
                request.getDescription(false));
        System.out.println(errorDetails.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.CREATED);
    }

    @ExceptionHandler(OkNotException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(OkNotException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.OK.value(), ex.getMessage(),
                request.getDescription(false));
        System.out.println(errorDetails.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.OK);
    }


}
