package com.jackathan.desquaredcalculator.feature.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InfixToPostfixTest {
    private InfixToPostfixInter infixToPostfix;

    @Before
    public void setUp() {
        infixToPostfix = new InfixToPostfix();
    }

    @After
    public void tearDown() {
        infixToPostfix = null;
    }

    @Test
    public void testSimpleInfixToPostfix() {
        assertEquals(createExpectedList("AB+C-"), infixToPostfix.transformInfixToPostFix("A+B-C"));
        assertEquals(createExpectedList("ABC+*"), infixToPostfix.transformInfixToPostFix("A*(B+C)"));
        assertEquals(createExpectedList("ABCD-*+"), infixToPostfix.transformInfixToPostFix("A+B*(C-D)"));
    }

    @Test
    public void testComplicatedInfixToPostfix() {
        assertEquals(createExpectedList("ABC+*DEF+/-"), infixToPostfix.transformInfixToPostFix("A*(B+C)-D/(E+F)"));
        assertEquals(createExpectedList("AB/C-DE*+AC*-"), infixToPostfix.transformInfixToPostFix("A/B-C+D*E-A*C"));
        assertEquals(createExpectedList("AB+C*D+EF+/G*"), infixToPostfix.transformInfixToPostFix("((A+B)*C+D)/(E+F)*G"));
    }

    @Test
    public void testThingsWentWeirdInfixToPostfix() {
        assertEquals(createExpectedList(""), infixToPostfix.transformInfixToPostFix(""));
        assertEquals(createExpectedList("A"), infixToPostfix.transformInfixToPostFix("A"));
        assertEquals(createExpectedList(""), infixToPostfix.transformInfixToPostFix("(((((((())))))))"));
        assertEquals(createExpectedList("AB+"), infixToPostfix.transformInfixToPostFix("A+B*"));
    }

    @Test
    public void test_wrongExpression() {
        assertEquals(createExpectedList("Error"), infixToPostfix.transformInfixToPostFix("((())"));
    }

    private List<String> createExpectedList(String postfixExpression) {
        List<String> expected = new ArrayList<>();
        switch (postfixExpression) {
            case "AB+C-":
                expected.add("A");
                expected.add("B");
                expected.add("+");
                expected.add("C");
                expected.add("-");
                break;
            case "ABC+*":
                expected.add("A");
                expected.add("B");
                expected.add("C");
                expected.add("+");
                expected.add("*");
                break;
            case "ABCD-*+":
                expected.add("A");
                expected.add("B");
                expected.add("C");
                expected.add("D");
                expected.add("-");
                expected.add("*");
                expected.add("+");
                break;
            case "ABC+*DEF+/-":
                expected.add("A");
                expected.add("B");
                expected.add("C");
                expected.add("+");
                expected.add("*");
                expected.add("D");
                expected.add("E");
                expected.add("F");
                expected.add("+");
                expected.add("/");
                expected.add("-");
                break;
            case "AB/C-DE*+AC*-":
                expected.add("A");
                expected.add("B");
                expected.add("/");
                expected.add("C");
                expected.add("-");
                expected.add("D");
                expected.add("E");
                expected.add("*");
                expected.add("+");
                expected.add("A");
                expected.add("C");
                expected.add("*");
                expected.add("-");
                break;
            case "AB+C*D+EF+/G*":
                expected.add("A");
                expected.add("B");
                expected.add("+");
                expected.add("C");
                expected.add("*");
                expected.add("D");
                expected.add("+");
                expected.add("E");
                expected.add("F");
                expected.add("+");
                expected.add("/");
                expected.add("G");
                expected.add("*");
                break;
            case "A":
                expected.add("A");
                break;
            case "Error":
                expected.add("Not valid expression");
                break;
            case "AB+":
                expected.add("A");
                expected.add("B");
                expected.add("+");
        }
        return expected;
    }
}
