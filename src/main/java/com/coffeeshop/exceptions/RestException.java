package com.coffeeshop.exceptions;

public class RestException extends BaseException{

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }
}
