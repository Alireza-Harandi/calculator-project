package com.example.calculatorr.exception;

public class NotDefinedVariableException extends EquationException {
    public NotDefinedVariableException (String msg){
        super(msg);
    }
    public NotDefinedVariableException (){
        super("Not Defined Variable");
    }
}
