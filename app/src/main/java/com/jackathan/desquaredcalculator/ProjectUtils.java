package com.jackathan.desquaredcalculator;

import java.util.ArrayList;
import java.util.List;

public class ProjectUtils {

    public static List<String> ConvertStringToList(String input) {
        List<String> output = new ArrayList<>();
        String entry = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isNumber(c)) {
                entry = entry + c;
            } else {
                if (!entry.isEmpty()) {
                    output.add(entry);
                    entry = "";
                }
                output.add(String.valueOf(c));
            }
        }
        if (!entry.isEmpty()) {
            output.add(entry);
        }
        return output;
    }

    private static boolean isNumber(char c) {
        switch (c) {
            case '*':
                return false;
            case '/':
                return false;
            case '(':
                return false;
            case '+':
                return false;
            case '-':
                return false;
            case ')':
                return false;
            default:
                return true;
        }
    }

    public static boolean isExpressionValid(String expression) {
        int countParentLeft = 0;
        int countParentRight = 0;
        for (char c : expression.toCharArray()) {
            switch (c) {
                case '(':
                    countParentLeft++;
                    break;
                case ')':
                    countParentRight++;
                    break;
            }
        }
        return countParentLeft == countParentRight;
    }
}
