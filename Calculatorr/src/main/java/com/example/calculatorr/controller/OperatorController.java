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
}
