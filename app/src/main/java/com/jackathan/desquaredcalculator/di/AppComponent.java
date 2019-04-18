package com.jackathan.desquaredcalculator.di;

import com.jackathan.desquaredcalculator.feature.calculator.CalculatorFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        PresenterModule.class})
public interface AppComponent {
    void inject(CalculatorFragment target);
}
