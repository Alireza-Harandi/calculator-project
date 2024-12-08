package com.example.calculatorr.exception;

public class InvalidInputException extends InputException {
    public InvalidInputException (String msg){
        super(msg);
    }
    public InvalidInputException (){
        super("Invalid Input");
    }
}
