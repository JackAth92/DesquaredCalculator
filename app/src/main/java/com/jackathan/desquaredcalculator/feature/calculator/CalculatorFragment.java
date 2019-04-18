package com.jackathan.desquaredcalculator.feature.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.jackathan.desquaredcalculator.DesquaredCalculatorApplication;
import com.jackathan.desquaredcalculator.R;

import java.util.Objects;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class CalculatorFragment extends Fragment implements CalculatorContract.View {
    @BindView(R.id.expression)
    TextView expressionEditText;
    @BindView(R.id.result)
    TextView tempResult;

    @Inject
    CalculatorContract.Presenter presenter;

    @OnClick(R.id.zero)
    public void btnZeroPressed() {
        presenter.zeroBtnPressed();
    }

    @OnClick({R.id.one})
    public void btnOnePressed() {
        presenter.numberBtnPressed("1");
    }

    @OnClick({R.id.two})
    public void btnTwoPressed() {
        presenter.numberBtnPressed("2");
    }

    @OnClick({R.id.three})
    public void btnThreePressed() {
        presenter.numberBtnPressed("3");
    }

    @OnClick({R.id.four})
    public void btnFourPressed() {
        presenter.numberBtnPressed("4");
    }

    @OnClick({R.id.five})
    public void btnFivePressed() {
        presenter.numberBtnPressed("5");
    }

    @OnClick({R.id.six})
    public void btnSixPressed() {
        presenter.numberBtnPressed("6");
    }

    @OnClick({R.id.seven})
    public void btnSevenPressed() {
        presenter.numberBtnPressed("7");
    }

    @OnClick({R.id.eight})
    public void btnEightPressed() {
        presenter.numberBtnPressed("8");
    }

    @OnClick({R.id.nine})
    public void btnNinePressed() {
        presenter.numberBtnPressed("9");
    }

    @OnClick(R.id.dot)
    public void btnCommaPressed() {
        presenter.commaBtnPressed();
    }

    @OnClick(R.id.addition)
    public void btnAdditionPressed() {
        presenter.operatorPressed("+");
    }

    @OnClick(R.id.subtraction)
    public void banSubtractionPressed() {
        presenter.operatorPressed("-");
    }

    @OnClick(R.id.multiplication)
    public void btnMultiplicationPressed() {
        presenter.operatorPressed("*");
    }

    @OnClick(R.id.division)
    public void btnDivisionPressed() {
        presenter.operatorPressed("/");
    }

    @OnClick(R.id.leftParen)
    public void btnLeftParenPressed() {
        presenter.leftParenPressed();
    }

    @OnClick(R.id.rightParen)
    public void btnRightParenPressed() {
        presenter.rightParenPressed();
    }

    @OnClick(R.id.equal)
    public void btnEqualPressed() {
        presenter.equalBtnPressed();
    }

    @OnLongClick(R.id.delete)
    public void btnDeleteLongPressed() {
        presenter.clearExpression();
    }

    @OnClick({R.id.delete})
    public void btnDeletePressed() {
        presenter.removeLastChar();
    }

    public CalculatorFragment() {
        // Required empty public constructor
    }


    public static CalculatorFragment newInstance() {
        CalculatorFragment fragment = new CalculatorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DesquaredCalculatorApplication) Objects.requireNonNull(getActivity()).getApplication())
                .getAppComponent().inject(this);
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this, view);
        expressionEditText.setShowSoftInputOnFocus(false);
        return view;
    }

    @BindView(R.id.scrollResult)
    HorizontalScrollView scrollResult;

    @Override
    public void showResult(String result) {
        tempResult.setText(result);
        scrollResult.postDelayed(() -> scrollResult.fullScroll(View.FOCUS_LEFT), 100L);
    }

    @Override
    public void updateExpression(String expression) {
        expressionEditText.setText(expression);
    }
}
