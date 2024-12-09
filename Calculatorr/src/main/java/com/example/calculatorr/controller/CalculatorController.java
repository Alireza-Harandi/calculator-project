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
    }

    public static CalculatorController getCalculatorController() {
        if (calculatorController == null) {
            calculatorController = new CalculatorController();
        }
        return calculatorController;
    }

}