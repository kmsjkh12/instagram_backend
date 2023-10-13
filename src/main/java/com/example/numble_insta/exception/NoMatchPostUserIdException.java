package com.example.numble_insta.exception;

public class NoMatchPostUserIdException extends RuntimeException{
    public NoMatchPostUserIdException(){}
    public NoMatchPostUserIdException(String message){
        super(message);
    }
}
