package com.example.numble_insta.exception;

public class ExistPostException extends RuntimeException{
    public ExistPostException(){}
    public ExistPostException(String message){
        super(message);
    }
}
