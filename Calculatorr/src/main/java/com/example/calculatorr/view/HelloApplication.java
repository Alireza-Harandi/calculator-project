package com.example.calculatorr.view;

import com.example.calculatorr.controller.CalculatorController;

import java.util.Scanner;

public class HelloApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] inputs = new String[n];

        for (int i = 0; i < n; i++) {
            inputs[i] = sc.nextLine();
        }

        System.out.println(CalculatorController.getCalculatorController().calculate(n, inputs));
    }
}