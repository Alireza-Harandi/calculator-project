package com.example.calculatorr.controller;

import com.example.calculatorr.exception.*;
import com.example.calculatorr.model.*;

import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Set;
import java.util.stream.Collectors;

public class CalculatorController implements CalculationService{
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

    @Override
    public String calculate(int n, String[] inputs) {
        equations = new Queue<>(n);
        results = new String[n];
        DoublyLinkedList<String> parts;
        DoublyLinkedList<String> postfix;
        String result;

        if (n==1 && typeOfExpression(inputs[0])) {
            try {
                ArithmeticExpression expression = new ArithmeticExpression(inputs[0]);
                parts = parseEquation(expression.getPhrase());
                postfix = toPostfix(parts);
                result = solvePostfix(postfix);
                addResult("", result);

                return String.join("\n", results);
            } catch (InvalidFormatException | InvalidInputException | ArithmeticErrorException |
                     CircularDependencyException | NotDefinedVariableException | InconsistencyException |
                     InvalidPropertiesFormatException e) {
                return e.getMessage();
            }
        }
        else {
            try {
                addEquations(n, inputs);

                for (int i = 0; i < n; i++) {
                    Equation equation = SelectEquation();

                    if (equation.getVariable() != '$') {
                        parts = parseEquation(equation.getPhrase());
                        postfix = toPostfix(parts);
                        result = solvePostfix(postfix);
                        addResult(String.valueOf(equation.getVariable()), result);
                    }
                }

                if (!equations.isEmpty() && hasCircularDependency(equations))
                    throw new CircularDependencyException();

                if (!equations.isEmpty())
                    throw new NotDefinedVariableException();

                return String.join("\n", results);

            } catch (InvalidFormatException | InvalidInputException | ArithmeticErrorException | CircularDependencyException | NotDefinedVariableException | InconsistencyException | InvalidPropertiesFormatException e) {
                return e.getMessage();
            }
        }
    }

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

    private void addResult(String variable, String inp) {
        for (String result : results) {
            if (result.indexOf(0) == variable.charAt(0))
                throw new InconsistencyException();
        }
        String phrase = "";
        if (Math.floor(Double.parseDouble(inp)) == Double.parseDouble(inp))
            phrase += String.valueOf((int) Double.parseDouble(inp));
        else
            phrase += String.valueOf(Math.round(Double.parseDouble(inp) * 10000.0) / 10000.0);
        OperandController.getOperandController().addOperand(new Operand(String.valueOf(variable), Double.parseDouble(inp)));
        if (variable.isEmpty())
            results[resultIndex++] = phrase;
        else
            results[resultIndex++] = variable+"="+phrase;
    }

    private String solvePostfix(DoublyLinkedList<String> parts) throws InvalidPropertiesFormatException {
        Stack<String> stack = new Stack<>();

        for (String part : parts) {
            if (isNumeric(part))
                stack.push(part);
            else {
                double result;

                if (part.equals("!")){
                    double num = Double.parseDouble(stack.pop());
                    UnaryOperator operator = new UnaryOperator(part.charAt(0), num);
                    isValid(operator);
                    result = OperatorController.getOperatorController().calculate(operator);
                }
                else {
                    double num1 = Double.parseDouble(stack.pop());
                    double num2 = Double.parseDouble(stack.pop());
                    BinaryOperator operator = new BinaryOperator(part.charAt(0), num2, num1);
                    isValid(operator);
                    result = OperatorController.getOperatorController().calculate(operator);
                }

                stack.push(String.valueOf(result));
            }
        }

        return stack.pop();
    }

    private void isValid(Operator operator) {
        if (operator instanceof UnaryOperator unaryOperator) {
            if (unaryOperator.getOperator() == '!'){
                if (!isNumeric(String.valueOf(unaryOperator.getFirstOperand())) || unaryOperator.getFirstOperand()<0)
                    throw new ArithmeticErrorException();
                if (unaryOperator.getFirstOperand() != Math.floor(unaryOperator.getFirstOperand()))
                    throw new ArithmeticErrorException();
            }
        }
        else if (operator instanceof BinaryOperator binaryOperator) {
            if (binaryOperator.getOperator() == '/'){
                if (!isNumeric(String.valueOf(binaryOperator.getFirstOperand())) || !isNumeric(String.valueOf(binaryOperator.getSecondOperand())) || binaryOperator.getSecondOperand()==0)
                    throw new ArithmeticErrorException();
            }
            else if (binaryOperator.getOperator() == '^') {
                if (!isNumeric(String.valueOf(binaryOperator.getFirstOperand())) || !isNumeric(String.valueOf(binaryOperator.getSecondOperand())))
                    throw new ArithmeticErrorException();

                double base = binaryOperator.getFirstOperand();
                double exponent = binaryOperator.getSecondOperand();
                if (base < 0 && exponent > 0 && (1 / exponent) % 2 == 0)
                    throw new ArithmeticErrorException();
            }
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void addEquations(int n, String[] inputs) {
        for (int i = 0; i < n; i++) {
            inputs[i] = inputs[i].chars()
                    .mapToObj(c -> (char) c)
                    .map(ch -> (ch >= 'a' && ch <= 'z') ? Character.toUpperCase(ch) : ch)
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            if( inputs[i].charAt(1) != '=' || !Character.isLetter(inputs[i].charAt(0)) )
                throw new InvalidFormatException();

            for (int j = 0; j < inputs[i].length(); j++) {
                char ch = inputs[i].charAt(j);

                if ( "+-*/^!".indexOf(ch) != -1){
                    if(j+1 != inputs[i].length()) {
                        char ch2 = inputs[i].charAt(j + 1);
                        if ("!".indexOf(ch) == -1 && "+-*/^!".indexOf(ch2) != -1)
                            throw new InvalidFormatException();
                    }
                }
            }

            equations.enqueue(new Equation(inputs[i].charAt(0), inputs[i].split("=")[1]));
        }
    }

    private DoublyLinkedList<String> parseEquation(String equation) {
        DoublyLinkedList<String> parts = new DoublyLinkedList<>();
        StringBuilder numberBuffer = new StringBuilder();
        int open =0;
        int close = 0;
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);

            if(ch == '(' && equation.charAt(i+1) == ')')
                throw new InvalidFormatException();

            if (ch == '(')
                open++;
            else if (ch == ')')
                close++;

            if (Character.isDigit(ch) || ch == '.') {
                numberBuffer.append(ch);
            }
            else if (ch == '(' && i + 1 < equation.length() && equation.charAt(i + 1) == '-') {
                i++;
                numberBuffer.append('-');

                while (i + 1 < equation.length() && (Character.isDigit(equation.charAt(i + 1)) || equation.charAt(i + 1) == '.')) {
                    i++;

                    if(equation.charAt(i) == '(')
                        open++;
                    else if (equation.charAt(i) == ')')
                        close++;

                    numberBuffer.append(equation.charAt(i));
                }

                if (!numberBuffer.isEmpty()) {
                    parts.add(numberBuffer.toString());
                    numberBuffer.setLength(0);
                }

                if (i + 1 < equation.length() && equation.charAt(i + 1) == ')') {
                    i++;
                    close++;
                }
            }
            else if ("+-*/^!()".indexOf(ch) != -1) {
                if (!numberBuffer.isEmpty()) {
                    parts.add(numberBuffer.toString());
                    numberBuffer.setLength(0);
                }
                parts.add(String.valueOf(ch));
            }
        }

        if (open != close)
            throw new InvalidFormatException();

        if (!numberBuffer.isEmpty()) {
            parts.add(numberBuffer.toString());
        }

        return parts;
    }

    private DoublyLinkedList<String> toPostfix(DoublyLinkedList<String> parts) throws InvalidPropertiesFormatException {
        Stack<String> stack = new Stack<>();
        DoublyLinkedList<String> result = new DoublyLinkedList<>();

        for (String inp : parts) {
            if (isNumeric(inp)){
                result.add(inp);
            }
            else if (inp.equals("(")){
                stack.push(inp);
            }
            else if (inp.equals(")")){
                while (stack.count() > 0 && !stack.peek().equals("(")){
                    result.add(stack.pop());
                }

                stack.pop();
            }
            else {
                while (stack.count() > 0 && priority(inp) <= priority(stack.peek()))
                {
                    result.add(stack.pop());
                }

                stack.push(inp);
            }
        }

        while (stack.count() > 0)
        {
            result.add(stack.pop());
        }
        return result;
    }

    private int priority(String temp) {
        return switch (temp) {
            case "-", "+" -> 1;
            case "/", "*" -> 2;
            case "!" -> 3;
            case "^" -> 4;
            default -> 0;
        };
    }
}