package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Operator {
    private char operator;

    public Operator(char operator) {
        this.operator = operator;
    }
}
