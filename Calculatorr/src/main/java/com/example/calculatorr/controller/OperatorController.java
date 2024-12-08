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

    public DoublyLinkedList<DefinedOperator> definedList = new DoublyLinkedList<>();

    private double factorial(double n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }

    public double calculate(Operator operator) {
        double result = 0;

        if (operator instanceof UnaryOperator operation) {
            result = factorial(operation.getFirstOperand());
        }

        else if (operator instanceof BinaryOperator operation) {
            result = switch (operation.getOperator()) {
                case '+' -> operation.getFirstOperand() + operation.getSecondOperand();
                case '-' -> operation.getFirstOperand() - operation.getSecondOperand();
                case '*' -> operation.getFirstOperand() * operation.getSecondOperand();
                case '/' -> operation.getFirstOperand() / operation.getSecondOperand();
                case '^' -> Math.pow(operation.getFirstOperand(), operation.getSecondOperand());
                default -> result;
            };
        }

        return result;
    }
}
