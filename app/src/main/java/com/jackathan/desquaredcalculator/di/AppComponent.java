package com.jackathan.desquaredcalculator.di;

import com.jackathan.desquaredcalculator.feature.calculator.CalculatorFragment;
import com.jackathan.desquaredcalculator.feature.converter.ConverterFragment;
import com.jackathan.desquaredcalculator.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        PresenterModule.class,
        NetworkModule.class})
public interface AppComponent {
    void inject(CalculatorFragment target);

    void inject(ConverterFragment target);
}
