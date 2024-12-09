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
}