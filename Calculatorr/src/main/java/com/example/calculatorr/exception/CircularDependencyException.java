package com.example.calculatorr.exception;

public class CircularDependencyException extends EquationException {
    public CircularDependencyException (String msg){
        super(msg);
    }
    public CircularDependencyException (){
        super("Circular Dependency");
    }
}
