package com.example.numble_insta.exception;

public class AlreadyFalseUserException extends RuntimeException{
    public AlreadyFalseUserException(){

    }
    public AlreadyFalseUserException(String message){
        super(message);
    }
}
