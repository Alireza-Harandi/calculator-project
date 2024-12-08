package com.example.calculatorr.controller;

import com.example.calculatorr.model.*;

public class OperandController {
    private static OperandController operandController;

    private OperandController() {
    }

    public static OperandController getOperandController() {
        if (operandController == null)
            operandController = new OperandController();
        return operandController;
    }
}
