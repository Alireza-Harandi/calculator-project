package com.example.calculatorr;

import com.example.calculatorr.controller.CalculatorController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestController {
    CalculatorController calculatorController = CalculatorController.getCalculatorController();

    @Test
    public void test1() {
        calculatorController.reset();

        String[] inputs = {"1+1"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "2");

    }

    @Test
    public void test10() {
        calculatorController.reset();

        String[] inputs = {"y=3490*345"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "Y=1204050");

    }

    @Test
    public void test11() {
        calculatorController.reset();

        String[] inputs = {"h=12345678/345"};

        String result = calculatorController.calculate(1, inputs);

        Assertions.assertEquals(result, "H=35784.5739");

    }

    @Test
    public void test12() {
        calculatorController.reset();

        String[] inputs = {"u=12983200-PI"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "U=12983196.8584");

    }

    @Test
    public void test13() {
        calculatorController.reset();

        String[] inputs = {"g=323^3"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "G=33698267");

    }

    @Test
    public void test14() {
        calculatorController.reset();

        String[] inputs = {"v=10308*1024"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "V=10555392");

    }

    @Test
    public void test15() {
        calculatorController.reset();

        String[] inputs = {"F=$+T"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "Invalid Input");

    }

    @Test
    public void test16() {
        calculatorController.reset();

        String[] inputs = {"-S=81*2"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "Invalid Format");

    }

    @Test
    public void test2() {
        calculatorController.reset();

        String[] inputs = {"b=3.5-4.2"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "B=-0.7");

    }

    @Test
    public void test3() {
        calculatorController.reset();

        String[] inputs = {"w=EN+PI"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "W=5.8599");

    }

    @Test
    public void test4() {
        calculatorController.reset();

        String[] inputs = {"x=43*100"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "X=4300");

    }

    @Test
    public void test5() {
        calculatorController.reset();

        String[] inputs = {"y=54/9"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "Y=6");

    }

    @Test
    public void test6() {
        calculatorController.reset();

        String[] inputs = {"r=7!"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "R=5040");

    }

    @Test
    public void test7() {
        calculatorController.reset();

        String[] inputs = {"t=5^0.5"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "T=2.2361");

    }

    @Test
    public void test8() {
        calculatorController.reset();

        String[] inputs = {"r=PI*4"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "R=12.5664");

    }

    @Test
    public void test9() {
        calculatorController.reset();

        String[] inputs = {"t=1/EN"};

        String result = calculatorController.calculate(inputs.length, inputs);

        Assertions.assertEquals(result, "T=0.3679");

    }
}
