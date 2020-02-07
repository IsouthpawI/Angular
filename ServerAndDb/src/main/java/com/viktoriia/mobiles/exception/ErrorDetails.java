package com.viktoriia.mobiles.exception;

import java.util.Date;


//Класс сокращающий выводы текстов в ошибках тоьлко таймстэмп сообщенне и детали
public class ErrorDetails {
    private Date timestamp;
    private int status;
    private String message;
    private String details;





    public ErrorDetails(Date timestamp, int status, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}