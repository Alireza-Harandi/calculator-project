package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operand {

    private String name;
    private double value;

    public Operand(String name, double value) {
        this.name = name;
        this.value = value;
    }
}
