package com.example.numble_insta.exception;

public class NoMatchCommentUserIdException extends RuntimeException {
    public NoMatchCommentUserIdException()
    {
    }
    public NoMatchCommentUserIdException (String message){
        super(message);
    }
}
