package com.coffeeshop.exceptions;

public class CardException extends BaseException{

    public CardException(String message) {
        super(message);
    }

    public CardException(String message, Throwable cause) {
        super(message, cause);
    }
}
