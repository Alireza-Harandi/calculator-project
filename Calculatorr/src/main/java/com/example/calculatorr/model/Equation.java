package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equation extends Expression {

    private char variable;
    public Equation(char variable, String phrase) {
        super(phrase);
        this.variable = variable;
    }
}
