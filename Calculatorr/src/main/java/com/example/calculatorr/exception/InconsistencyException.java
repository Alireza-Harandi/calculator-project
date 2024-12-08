package com.example.calculatorr.exception;

public class InconsistencyException extends EquationException{
    public InconsistencyException (String msg){
        super(msg);
    }
    public InconsistencyException (){
        super("Inconsistency");
    }
}
