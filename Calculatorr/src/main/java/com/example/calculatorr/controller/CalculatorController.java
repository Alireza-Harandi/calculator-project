package com.example.calculatorr.controller;

import com.example.calculatorr.exception.*;
import com.example.calculatorr.exception.ArithmeticException;
import com.example.calculatorr.model.*;

import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Set;
import java.util.stream.Collectors;

public class CalculatorController{
    private static CalculatorController calculatorController;

    private CalculatorController() {
        resultIndex = 0;
    }

    public static CalculatorController getCalculatorController() {
        if (calculatorController == null) {
            calculatorController = new CalculatorController();
        }
        return calculatorController;
    }

    private Queue<Equation> equations;
    private String[] results;
    private int resultIndex;

    private boolean hasCircularDependency(Queue<Equation> equations) {
        Set<String> variables = new HashSet<>();
        Set<String> notDefine = new HashSet<>();
        int n = equations.count();

        for (int i = 0; i < n; i++) {
            Equation equation = equations.dequeue();
            variables.add(String.valueOf(equation.getVariable()));

            for (char ch : equation.getPhrase().toCharArray()) {
                if (Character.isLetter(ch)) {
                    notDefine.add(String.valueOf(ch));
                }
            }
        }

        for (String item : variables) {
            if (!notDefine.contains(item)) {
                return false;
            }
        }

        for (String item : notDefine) {
            if (!variables.contains(item)) {
                return false;
            }
        }

        return true;
    }

    private Equation SelectEquation() {
        int count;
        int n = equations.count();

        for (int j = 0; j < n; j++) {
            Equation equation = equations.dequeue();
            int m = equation.getPhrase().length();
            for (int i = 0; i < m; i++) {
                count = 0;
                char ch1 = equation.getPhrase().charAt(i);
                String name = "";

                if (Character.isLetter(ch1)) {
                    count++;
                    char ch2 = 0;

                    if (i+1 < m)
                        ch2 = equation.getPhrase().charAt(i + 1);

                    if (Character.isLetter(ch2)) {
                        name += ch1 + ch2;
                        count++;
                    } else {
                        name += ch1;
                    }

                    boolean define = false;

                    for (Operand operand : OperandController.getOperandController().operands) {

                        if (operand.getName().equalsIgnoreCase(name)) {
                            define = true;

                            if (count == 1)
                                equation.setPhrase(equation.getPhrase().substring(0, i) + operand.getValue() + equation.getPhrase().substring(i + 1));
                            else
                                equation.setPhrase(equation.getPhrase().substring(0, i) + operand.getValue() + equation.getPhrase().substring(i + 2));
                            break;
                        }
                    }

                    if (!define){
                        checkInput(name);
                        break;
                    }

                }

                if (i == m - 1)
                    return equation;
            }
            equations.enqueue(equation);
        }

        return new Equation('$', "");
    }

    private void checkInput(String input) {
        if (!Character.isUpperCase(input.charAt(0)))
            throw new InvalidInputException();
    }

    private boolean typeOfExpression(String expression) {
        return expression.charAt(1) != '=';
    }
}