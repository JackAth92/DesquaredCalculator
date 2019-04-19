package com.jackathan.desquaredcalculator.feature.converter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.jackathan.desquaredcalculator.DesquaredCalculatorApplication;
import com.jackathan.desquaredcalculator.R;

import java.util.Objects;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class ConverterFragment extends Fragment implements ConverterContract.View {
    @BindView(R.id.fromSpinner)
    Spinner fromSpinner;
    @BindView(R.id.toSpinner)
    Spinner toSpinner;
    @BindView(R.id.amountEt)
    EditText amount;
    @BindView(R.id.convertedAmountTv)
    TextView convertedAmountTv;
    @Inject
    ConverterContract.Presenter presenter;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @OnTextChanged(R.id.amountEt)
    public void textChanged() {
        presenter.setAmount(amount.getText().toString());
        presenter.convert();
    }

    public ConverterFragment() {
        // Required empty public constructor
    }

    public static ConverterFragment newInstance() {
        ConverterFragment fragment = new ConverterFragment();

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        ButterKnife.bind(this, view);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.currencies, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(arrayAdapter);
        toSpinner.setAdapter(arrayAdapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setFromCurrency((String) parent.getItemAtPosition(position));
                presenter.convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setToCurrency((String) parent.getItemAtPosition(position));
                presenter.convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }


    @Override
    public void showConvertedAmount(String convertedAmount) {
        convertedAmountTv.setText(convertedAmount);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

}
