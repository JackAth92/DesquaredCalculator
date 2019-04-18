package com.jackathan.desquaredcalculator.feature.calculator;


import com.jackathan.desquaredcalculator.ProjectUtils;

import javax.inject.Inject;

public class CalculatorPresenter implements CalculatorContract.Presenter {
    private CalculatePostfixInter calculatePostfix;
    private InfixToPostfixInter infixToPostfix;
    private CalculatorContract.View view;
    private String expression;
    private String result;

    @Inject
    public CalculatorPresenter(CalculatePostfixInter calculatePostfix, InfixToPostfixInter infixToPostfix) {
        this.calculatePostfix = calculatePostfix;
        this.infixToPostfix = infixToPostfix;
        expression = "";
        result = "Result";
    }

    @Override
    public void setView(CalculatorContract.View view) {
        this.view = view;
    }

    @Override
    public void calculate() {
        if (!expression.isEmpty()) {
            result = calculatePostfix.calculate(infixToPostfix.transformInfixToPostFix(expression));
        } else {
            result = "Result";
        }
        if (result != null) {
            view.showResult(result);
        } else {
            result = "Result";
            view.showResult(result);
        }
    }

    @Override
    public void numberBtnPressed(String number) {
        if (!expression.isEmpty() && result.equals("Result")) {
            expression = "";
        }
        expression = expression + number;
        view.updateExpression(expression);
        calculate();
    }

    @Override
    public void operatorPressed(String operator) {
        if (isLastCharAnOperator(expression) || isLastCharComma(expression)) {
            if (!expression.isEmpty()) {
                expression = expression.substring(0, expression.length() - 1) + operator;
            }
        } else if (!expression.isEmpty()) {
            expression = expression + operator;
        }
        view.updateExpression(expression);
        calculate();
    }

    private boolean isLastCharComma(String expression) {
        return getLastCharacter(expression) == '.';
    }

    @Override
    public void zeroBtnPressed() {
        if (expression.isEmpty() || isLastCharLeftParen(expression) || isLastCharAnOperator(expression)) {
            view.updateExpression(expression);
        } else {
            expression = expression + "0";
            view.updateExpression(expression);
        }
        calculate();
    }

    @Override
    public void commaBtnPressed() {
        if (expression.isEmpty() || isLastCharLeftParen(expression) || isLastCharAnOperator(expression)) {
            expression = expression + "0.";
            view.updateExpression(expression);
        } else if (!isLastNumberDecimal(expression)) {
            expression = expression + ".";
            view.updateExpression(expression);
        }
        calculate();
    }

    @Override
    public void leftParenPressed() {
        if (expression.isEmpty() || isLastCharLeftParen(expression) || isLastCharAnOperator(expression)) {
            expression = expression + "(";
            view.updateExpression(expression);
        } else {
            expression = expression + "*(";
            view.updateExpression(expression);
        }
    }

    @Override
    public void clearExpression() {
        expression = "";
        result = "Result";
        view.showResult(result);
        view.updateExpression(expression);
    }

    @Override
    public void removeLastChar() {
        if (!expression.isEmpty()) {
            expression = expression.substring(0, expression.length() - 1);
            view.updateExpression(expression);
            calculate();
        }
    }

    @Override
    public void equalBtnPressed() {
        if (!expression.isEmpty()) {
            expression = calculatePostfix.calculate(infixToPostfix.transformInfixToPostFix(expression));
            view.updateExpression(expression);
            result = "Result";
            view.showResult(result);
        }
    }

    @Override
    public void rightParenPressed() {
        if (!expression.isEmpty()) {
            expression = expression + ')';
            view.updateExpression(expression);
            calculate();
            expression = expression + "*";
        }
    }


    private boolean isLastCharAnOperator(String expression) {
        return ProjectUtils.isOperator(getLastCharacter(expression));
    }

    private char getLastCharacter(String expression) {
        if (!expression.isEmpty()) {
            return expression.charAt(expression.length() - 1);
        } else {
            return '\u0000';
        }
    }

    private boolean isLastCharLeftParen(String expression) {
        return getLastCharacter(expression) == '(';
    }

    private boolean isLastNumberDecimal(String expression) {
        if (expression.isEmpty()) {
            return false;
        } else {
            if (isLastCharComma(expression)) {
                return true;
            } else if (isLastCharAnOperator(expression) || isLastCharLeftParen(expression) || isLastCharRightParen(expression)) {
                return false;
            } else {
                return isLastNumberDecimal(expression.substring(0, expression.length() - 1));
            }
        }
    }

    private boolean isLastCharRightParen(String expression) {
        return getLastCharacter(expression) == ')';
    }
}
