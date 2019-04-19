package com.jackathan.desquaredcalculator.feature.converter;

public interface ConverterContract {
    interface Presenter {
        void setView(ConverterContract.View view);

        void setFromCurrency(String fromCurrency);

        void setToCurrency(String toCurrency);

        void setAmount(String amount);

        void convert();

        void dispose();
    }

    interface View {
        void showConvertedAmount(String convertedAmount);

        void showErrorMessage();

        void hideProgressbar();

        void showProgressBar();
    }
}
