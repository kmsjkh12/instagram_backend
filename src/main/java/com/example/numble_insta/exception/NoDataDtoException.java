package com.example.numble_insta.exception;

public class NoDataDtoException extends RuntimeException{

    public NoDataDtoException(){

    }
    public NoDataDtoException(String message){
        super(message);
    }
}
