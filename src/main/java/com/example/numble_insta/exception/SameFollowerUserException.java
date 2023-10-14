package com.example.numble_insta.exception;

public class SameFollowerUserException extends RuntimeException{
    public SameFollowerUserException(){}
    public SameFollowerUserException(String s) {
        super(s);
    }
}
