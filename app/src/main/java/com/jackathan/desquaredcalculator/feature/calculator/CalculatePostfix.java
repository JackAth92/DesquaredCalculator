package com.jackathan.desquaredcalculator.feature.calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class CalculatePostfix implements CalculatePostfixInter {
    private Stack<String> stack;

    public CalculatePostfix() {
        stack = new Stack<>();
    }

    @Override
    public String calculate(List<String> input) {
        stack.clear();
        for (String part : input) {
            if (isOperator(part)) {
                if (stack.size() <= 1) {
                    break;
                }
                BigDecimal num2 = new BigDecimal(stack.pop());
                BigDecimal num1 = new BigDecimal(stack.pop());
                String result = "";
                switch (part) {
                    case "+":
                        result = num1.add(num2).toString();
                        break;
                    case "-":
                        result = num1.subtract(num2).toString();
                        break;
                    case "*":
                        result = num1.multiply(num2).toString();
                        break;
                    case "/":
                        if (num2.equals(new BigDecimal(0))) {
                            return "The last time I checked division with 0 was not possible";
                        }
                        result = num1.divide(num2, 250, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toString();
                }
                stack.push(result);
            } else {
                stack.push(part);
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String part) {
        switch (part) {
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
            default:
                return false;
        }
    }


}
