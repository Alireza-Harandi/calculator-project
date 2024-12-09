package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryOperator extends Operator {

    private double firstOperand;
    private double secondOperand;

    public BinaryOperator(char operator, double firstOperand, double secondOperand) {
        super(operator);
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }
}
