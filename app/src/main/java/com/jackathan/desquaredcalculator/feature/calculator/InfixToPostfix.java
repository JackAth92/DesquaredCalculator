package com.jackathan.desquaredcalculator.feature.calculator;

import com.jackathan.desquaredcalculator.ProjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix implements InfixToPostfixInter {

    private static final int HIGH_PRIORITY = 2;
    private static final int LOW_PRIORITY = 1;
    private Stack<String> stack;
    private List<String> postFix;

    public InfixToPostfix() {
        stack = new Stack<>();
    }

    @Override
    public List<String> transformInfixToPostFix(String infix) {
        stack = new Stack<>();
        postFix = new ArrayList<>();
        if (ProjectUtils.isExpressionValid(trimInfix(infix))) {
            for (String part : ProjectUtils.ConvertStringToList(trimInfix(infix))) {
                switch (part) {
                    case "+":
                    case "-":
                        gotOper(part, LOW_PRIORITY);
                        break;
                    case "*":
                    case "/":
                        gotOper(part, HIGH_PRIORITY);
                        break;
                    case "(":
                        stack.push(part);
                        break;
                    case ")":
                        gotParen();
                        break;
                    default:
                        postFix.add(part);
                        break;
                }
            }
            while (!stack.isEmpty()) {
                postFix.add(stack.pop());
            }
        } else {
            postFix.add("Not valid expression");
        }
        return postFix;
    }

    private void gotOper(String opThis, int operatorPriority) {
        while (!stack.isEmpty()) {
            String opTop = stack.pop();
            if (opTop.equals("(")) {
                stack.push(opTop);
                break;
            } else {
                if (operatorPriority > returnPriority(opTop)) {
                    stack.push(opTop);
                    break;
                } else {
                    postFix.add(opTop);
                }
            }
        }
        stack.push(opThis);
    }

    private void gotParen() {
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                stack.pop();
                break;
            } else {
                postFix.add(stack.pop());
            }
        }
    }

    private int returnPriority(String oper) {
        if (oper.equals("+") || oper.equals("-")) {
            return LOW_PRIORITY;
        } else {
            return HIGH_PRIORITY;
        }
    }

    private String trimInfix(String infix) {
        if (!infix.isEmpty()) {
            if (ProjectUtils.isOperator(infix.charAt(infix.length() - 1))) {
                infix = infix.substring(0, infix.length() - 1);
            }
        }
        return infix;
    }
}
