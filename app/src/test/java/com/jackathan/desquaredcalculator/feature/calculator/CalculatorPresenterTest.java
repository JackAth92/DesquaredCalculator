package com.jackathan.desquaredcalculator.feature.calculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


public class CalculatorPresenterTest {
    private CalculatorContract.Presenter presenter;
    @Mock
    private CalculatorContract.View view;
    @Mock
    private CalculatePostfixInter calculatePostfix;
    @Mock
    private InfixToPostfixInter infixToPostfix;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new CalculatorPresenter(calculatePostfix, infixToPostfix);
        presenter.setView(view);
    }

    @Test
    public void test_calculateAndShowResult() {
        List<String> postfix = new ArrayList<>();
        postfix.add("3");
        postfix.add("5");
        postfix.add("+");
        Mockito.when(infixToPostfix.transformInfixToPostFix("3+5")).thenReturn(postfix);
        Mockito.when(calculatePostfix.calculate(postfix)).thenReturn("8");
        presenter.numberBtnPressed("3");
        Mockito.verify(view).updateExpression("3");
        presenter.operatorPressed("+");
        Mockito.verify(view).updateExpression("3+");
        presenter.numberBtnPressed("5");
        Mockito.verify(view).updateExpression("3+5");
        Mockito.verify(view).showResult("8");
    }

    @Test
    public void test_numberPressed() {
        presenter.numberBtnPressed("2");
        Mockito.verify(view).updateExpression("2");
        presenter.numberBtnPressed("4");
        Mockito.verify(view).updateExpression("24");
    }

    @Test
    public void test_operatorPressedWithNoNumbersPreviouslyAdded() {
        presenter.operatorPressed("+");
        Mockito.verify(view).updateExpression("");
    }

    @Test
    public void test_operatorPressedWithNumbersPreviouslyAdded() {
        presenter.numberBtnPressed("2");
        presenter.numberBtnPressed("4");
        presenter.operatorPressed("+");
        Mockito.verify(view).updateExpression("24+");
        presenter.numberBtnPressed("3");
        Mockito.verify(view).updateExpression("24+3");
    }

    @Test
    public void test_twoOperatorsInARowShouldKeepTheLastOne() {
        presenter.numberBtnPressed("2");
        presenter.numberBtnPressed("4");
        presenter.operatorPressed("+");
        presenter.operatorPressed("*");
        presenter.operatorPressed("/");
        presenter.operatorPressed("-");
        presenter.numberBtnPressed("3");
        Mockito.verify(view).updateExpression("24-3");
    }

    @Test
    public void test_zeroPressed() {
        presenter.zeroBtnPressed();
        Mockito.verify(view).updateExpression("");
        presenter.numberBtnPressed("1");
        presenter.numberBtnPressed("3");
        presenter.zeroBtnPressed();
        Mockito.verify(view).updateExpression("130");
        presenter.operatorPressed("+");
        presenter.zeroBtnPressed();
        presenter.zeroBtnPressed();
        presenter.numberBtnPressed("9");
        Mockito.verify(view).updateExpression("130+9");
    }

    @Test
    public void test_leftParenPressed() {
        presenter.leftParenPressed();
        Mockito.verify(view).updateExpression("(");
    }

    @Test
    public void test_leftParentAfterNumber() {
        presenter.numberBtnPressed("3");
        presenter.leftParenPressed();
        Mockito.verify(view).updateExpression("3*(");
    }

    @Test
    public void test_zeroPressedAfterLeftParen() {
        presenter.leftParenPressed();
        presenter.zeroBtnPressed();
        presenter.zeroBtnPressed();
        presenter.numberBtnPressed("3");
        Mockito.verify(view).updateExpression("(3");
    }

    @Test
    public void test_commaPressed() {
        presenter.commaBtnPressed();
        Mockito.verify(view).updateExpression("0.");
        presenter.commaBtnPressed();
        Mockito.verify(view).updateExpression("0.");
    }

    @Test
    public void test_multipleCommaPresses() {
        presenter.numberBtnPressed("3");
        presenter.numberBtnPressed("4");
        presenter.commaBtnPressed();
        presenter.zeroBtnPressed();
        presenter.numberBtnPressed("1");
        presenter.commaBtnPressed();
        presenter.operatorPressed("*");
        presenter.numberBtnPressed("3");
        presenter.numberBtnPressed("1");
        presenter.commaBtnPressed();
        presenter.numberBtnPressed("2");
        presenter.commaBtnPressed();
        presenter.numberBtnPressed("4");
        Mockito.verify(view).updateExpression("34.01*31.24");
    }

    @Test
    public void test_commaPressedAfterNumber() {
        presenter.numberBtnPressed("9");
        presenter.commaBtnPressed();
        Mockito.verify(view).updateExpression("9.");
    }

    @Test
    public void test_deleteBtnLongPressed() {
        List<String> postfix = new ArrayList<>();
        postfix.add("1");
        Mockito.when(calculatePostfix.calculate(postfix)).thenReturn("1");
        presenter.numberBtnPressed("1");
        Mockito.verify(view).updateExpression("1");
        presenter.operatorPressed("+");
        postfix.add("+");
        Mockito.when(calculatePostfix.calculate(postfix)).thenReturn("1");
        Mockito.verify(view).updateExpression("1+");
        presenter.numberBtnPressed("4");
        Mockito.verify(view).updateExpression("1+4");
        postfix.add("4");
        Mockito.when(calculatePostfix.calculate(postfix)).thenReturn("5");
        //Mockito.verify(view).showResult("5");
        presenter.clearExpression();
        Mockito.verify(view).updateExpression("");
        Mockito.verify(view).showResult("Result");
    }

    @Test
    public void test_deleteBtnPressed() {
        presenter.numberBtnPressed("1");
        Mockito.verify(view).updateExpression("1");
        presenter.numberBtnPressed("1");
        Mockito.verify(view).updateExpression("11");
        presenter.numberBtnPressed("1");
        Mockito.verify(view).updateExpression("111");
        presenter.operatorPressed("+");
        Mockito.verify(view).updateExpression("111+");
        presenter.numberBtnPressed("3");
        Mockito.verify(view).updateExpression("111+3");
        presenter.numberBtnPressed("5");
        Mockito.verify(view).updateExpression("111+35");
        presenter.removeLastChar();
        Mockito.verify(view, Mockito.times(2)).updateExpression("111+3");
    }

    @Test
    public void test_equalBtnWasPressed() {
        List<String> postfix = new ArrayList<>();
        postfix.add("3");
        postfix.add("5");
        postfix.add("+");
        Mockito.when(infixToPostfix.transformInfixToPostFix("3+5")).thenReturn(postfix);
        Mockito.when(calculatePostfix.calculate(postfix)).thenReturn("8");
        presenter.numberBtnPressed("3");
        presenter.operatorPressed("+");
        presenter.numberBtnPressed("5");
        presenter.equalBtnPressed();
        Mockito.verify(view).showResult("8");
        Mockito.verify(view).updateExpression("8");
    }

    @Test
    public void test_rightParenPressed() {
        List<String> postfix = new ArrayList<>();
        postfix.add("2");
        postfix.add("3");
        postfix.add("5");
        postfix.add("+");
        postfix.add("*");
        Mockito.when(infixToPostfix.transformInfixToPostFix("2*(3+5)")).thenReturn(postfix);
        Mockito.when(calculatePostfix.calculate(postfix)).thenReturn("16");
        presenter.numberBtnPressed("2");
        presenter.operatorPressed("*");
        presenter.leftParenPressed();
        presenter.numberBtnPressed("3");
        presenter.operatorPressed("+");
        presenter.numberBtnPressed("5");
        presenter.rightParenPressed();
        Mockito.verify(view).showResult("16");
        Mockito.verify(view).updateExpression("2*(3+5)");
    }

    @Test
    public void text_resultShouldBeReplaceWhenNumbersIsPressed() {
        List<String> postfix = new ArrayList<>();
        postfix.add("2");
        postfix.add("3");
        postfix.add("5");
        postfix.add("+");
        postfix.add("*");
        Mockito.when(infixToPostfix.transformInfixToPostFix("2*(3+5)")).thenReturn(postfix);
        Mockito.when(calculatePostfix.calculate(postfix)).thenReturn("16");
        presenter.numberBtnPressed("2");
        presenter.operatorPressed("*");
        presenter.leftParenPressed();
        presenter.numberBtnPressed("3");
        presenter.operatorPressed("+");
        presenter.numberBtnPressed("5");
        presenter.rightParenPressed();
        Mockito.verify(view).showResult("16");
        Mockito.verify(view).updateExpression("2*(3+5)");
        presenter.equalBtnPressed();
        Mockito.verify(view,Mockito.times(5)).showResult("Result");
        Mockito.verify(view).updateExpression("16");
        presenter.numberBtnPressed("4");
        Mockito.verify(view).updateExpression("4");
    }
}
