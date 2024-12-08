package com.example.calculatorr.controller;

import com.example.calculatorr.model.*;

public class OperatorController {
    private static OperatorController operatorController;

    private OperatorController() {
    }

    public static OperatorController getOperatorController() {
        if (operatorController == null) {
            operatorController = new OperatorController();
        }
        return operatorController;
    }

    private double factorial(double n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }
}
