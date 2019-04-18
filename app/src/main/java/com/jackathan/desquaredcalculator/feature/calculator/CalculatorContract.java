package com.jackathan.desquaredcalculator.feature.calculator;

public interface CalculatorContract {
    interface Presenter {

        void setView(View view);

        void calculate();

        void numberBtnPressed(String number);

        void operatorPressed(String operator);

        void zeroBtnPressed();

        void commaBtnPressed();

        void leftParenPressed();

        void clearExpression();

        void removeLastChar();

        void equalBtnPressed();

        void rightParenPressed();
    }

    interface View {

        void showResult(String result);

        void updateExpression(String expression);
    }
}
