package com.example.numble_insta.exception;

public class ExistUserException extends RuntimeException{

    public ExistUserException(){

    }
    public ExistUserException(String message){
        super(message);
    }
}
