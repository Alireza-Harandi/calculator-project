package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Expression {
    private String phrase;

    public Expression(String phrase) {
        this.phrase = phrase;
    }
}
