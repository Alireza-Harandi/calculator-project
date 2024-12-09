package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnaryOperator extends Operator {

    private double firstOperand;

    public UnaryOperator(char operator, double firstOperand) {
        super(operator);
        this.firstOperand = firstOperand;
    }
}
