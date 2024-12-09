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

    public DoublyLinkedList<Operand> operands = new DoublyLinkedList<>();

    static {
        OperandController.getOperandController().operands.add(new Operand("PI", 3.14159));
        OperandController.getOperandController().operands.add(new Operand("EN", 2.71828));
    }

    public void addOperand(Operand operand) {
        operands.add(operand);
    }
}
