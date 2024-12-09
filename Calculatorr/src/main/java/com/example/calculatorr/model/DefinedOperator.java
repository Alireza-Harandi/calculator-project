package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefinedOperator extends Operator {

    private char firstOperand;
    private char secondOperand;
    private String defined;

    public DefinedOperator(char operator, char firstOperand, char secondOperand, String defined) {
        super(operator);
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.defined = defined;
    }
}
