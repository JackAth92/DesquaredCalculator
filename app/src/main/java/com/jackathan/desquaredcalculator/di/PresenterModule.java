package com.jackathan.desquaredcalculator.di;

import com.jackathan.desquaredcalculator.feature.calculator.CalculatePostfix;
import com.jackathan.desquaredcalculator.feature.calculator.CalculatorContract;
import com.jackathan.desquaredcalculator.feature.calculator.CalculatorPresenter;
import com.jackathan.desquaredcalculator.feature.calculator.InfixToPostfix;
import com.jackathan.desquaredcalculator.feature.converter.ConverterContract;
import com.jackathan.desquaredcalculator.feature.converter.ConverterPresenter;
import com.jackathan.desquaredcalculator.network.FixerService;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    public CalculatorContract.Presenter provideCalculatorPresenter() {
        return new CalculatorPresenter(new CalculatePostfix(), new InfixToPostfix());
    }

    @Provides
    public ConverterContract.Presenter provideConverterPresenter(FixerService fixerService) {
        return new ConverterPresenter(fixerService);
    }
}
