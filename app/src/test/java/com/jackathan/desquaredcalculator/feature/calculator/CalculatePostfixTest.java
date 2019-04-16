package com.jackathan.desquaredcalculator.feature.calculator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculatePostfixTest {

    private CalculatePostfixInter calculatePostfix;

    @Before
    public void setUp() {
        calculatePostfix = new CalculatePostfix();
    }

    @After
    public void tearDown() {
        calculatePostfix = null;
    }

    @Test
    public void test_addition() {
        List<String> input = new ArrayList<>();
        input.add("2");
        input.add("3");
        input.add("+");
        Assert.assertEquals("5", calculatePostfix.calculate(input));
    }

    @Test
    public void test_subtraction() {
        List<String> input = new ArrayList<>();
        input.add("8");
        input.add("3");
        input.add("-");
        Assert.assertEquals("5", calculatePostfix.calculate(input));
    }

    @Test
    public void test_multiplication() {
        List<String> input = new ArrayList<>();
        input.add("2");
        input.add("3");
        input.add("*");
        Assert.assertEquals("6", calculatePostfix.calculate(input));
    }

    @Test
    public void test_division() {
        List<String> input = new ArrayList<>();
        input.add("6");
        input.add("3");
        input.add("/");
        Assert.assertEquals("2", calculatePostfix.calculate(input));
    }

    @Test
    public void test_combination() {
        List<String> input = new ArrayList<>();
        input.add("3");
        input.add("6");
        input.add("+");
        input.add("3");
        input.add("/");
        Assert.assertEquals("3", calculatePostfix.calculate(input));
    }

    @Test
    public void test_additionDoubleWithInter() {
        List<String> input = new ArrayList<>();
        input.add("2.13");
        input.add("3");
        input.add("+");
        Assert.assertEquals("5.13", calculatePostfix.calculate(input));
    }

    @Test
    public void test_subtractionDoubleWithInter() {
        List<String> input = new ArrayList<>();
        input.add("8.13");
        input.add("3");
        input.add("-");
        Assert.assertEquals("5.13", calculatePostfix.calculate(input));
    }

    @Test
    public void test_multiplicationDoubleWithInter() {
        List<String> input = new ArrayList<>();
        input.add("8.13");
        input.add("3");
        input.add("*");
        Assert.assertEquals("24.39", calculatePostfix.calculate(input));
    }

    @Test
    public void test_divisionDoubleWithInter() {
        List<String> input = new ArrayList<>();
        input.add("5");
        input.add("2");
        input.add("/");
        Assert.assertEquals("2.5", calculatePostfix.calculate(input));
    }

    @Test
    public void test_additionBigInter() {
        List<String> input = new ArrayList<>();
        input.add("502682145789325418965234789");
        input.add("1548623154784233658952127789");
        input.add("+");
        Assert.assertEquals("2051305300573559077917362578", calculatePostfix.calculate(input));
        input.clear();
        input.add("1000000000000000");
        input.add("1000000000000000");
        input.add("+");
        Assert.assertEquals("2000000000000000", calculatePostfix.calculate(input));
    }

    @Test
    public void test_combinationDoubleWithInter() {
        List<String> input = new ArrayList<>();
        input.add("502682145789325418965234789");
        input.add("1548623154784233658952127789");
        input.add("+");
        input.add("2.5641235");
        input.add("/");
        Assert.assertEquals("800002535202988107989869668.134159684586175353878235584206454954295298178890369360134174504465171041878443062512394586298202875173524208174840252429338914447763534010744802268689476150427231761652666105981244663137325483737425283922556772323953974915794812535355648821127375495"
                , calculatePostfix.calculate(input));
    }

    @Test
    public void test_divisionWithZero() {
        List<String> input = new ArrayList<>();
        input.add("502682145789325418965234789");
        input.add("0");
        input.add("/");
        Assert.assertEquals("The last time I checked division with 0 was not possible", calculatePostfix.calculate(input));
    }

}
