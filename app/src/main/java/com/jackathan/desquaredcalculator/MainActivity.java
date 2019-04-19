package com.jackathan.desquaredcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jackathan.desquaredcalculator.feature.calculator.CalculatorFragment;
import com.jackathan.desquaredcalculator.feature.converter.ConverterFragment;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottomNavigationMenuView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.frag)
    FrameLayout frameLayout;
    private ConverterFragment converterFragment;
    private CalculatorFragment calculatorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        calculatorFragment = CalculatorFragment.newInstance();
        converterFragment = ConverterFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag, calculatorFragment, "Calculator").commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.calculator:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag, calculatorFragment, "Calculator").commit();
                    return true;
                case R.id.converter:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag, converterFragment, "Converter").commit();
                    return true;
            }
            return true;
        });
    }
}
