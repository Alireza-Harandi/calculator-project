package com.example.calculatorr.exception;

public class ArithmeticErrorException extends ArithmeticException {
    public ArithmeticErrorException (String msg){
        super(msg);
    }
    public ArithmeticErrorException (){
        super("Arithmetic Error");
    }
}
