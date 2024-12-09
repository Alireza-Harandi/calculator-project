package com.example.calculatorr.exception;

public class InvalidFormatException extends InputException {
    public InvalidFormatException (String msg){
        super(msg);
    }
    public InvalidFormatException (){
        super("Invalid Format");
    }
}
