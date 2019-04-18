package com.jackathan.desquaredcalculator;

import android.app.Application;

import com.jackathan.desquaredcalculator.di.AppComponent;
import com.jackathan.desquaredcalculator.di.AppModule;
import com.jackathan.desquaredcalculator.di.DaggerAppComponent;

public class DesquaredCalculatorApplication extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    private AppComponent initDagger(DesquaredCalculatorApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
