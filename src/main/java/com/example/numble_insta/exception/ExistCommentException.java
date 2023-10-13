package com.example.numble_insta.exception;

public class ExistCommentException extends RuntimeException{
    public ExistCommentException(){

    }
    public ExistCommentException(String message){
        super(message);
    }
}
