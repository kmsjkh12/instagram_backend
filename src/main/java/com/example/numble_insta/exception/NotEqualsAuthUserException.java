package com.example.numble_insta.exception;


public class NotEqualsAuthUserException extends RuntimeException{
    public NotEqualsAuthUserException(){}

    public NotEqualsAuthUserException(String message){
        super(message);
    }
}
