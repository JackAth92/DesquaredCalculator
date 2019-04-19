package com.jackathan.desquaredcalculator.feature.converter;

import com.jackathan.desquaredcalculator.network.FixerService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ConverterPresenter implements ConverterContract.Presenter {
    private FixerService fixerService;
    private ConverterContract.View view;
    private UIdata uIdata;
    private Disposable disposable;

    @Inject
    public ConverterPresenter(FixerService fixerService) {
        this.fixerService = fixerService;
        uIdata = new UIdata();
    }

    @Override
    public void setView(ConverterContract.View view) {
        this.view = view;
    }

    @Override
    public void setFromCurrency(String fromCurrency) {
        if (!fromCurrency.equals("None selected")) {
            uIdata.setCurrencyFrom(fromCurrency);
        }
    }

    @Override
    public void setToCurrency(String toCurrency) {
        if (!toCurrency.equals("None selected")) {
            uIdata.setCurrencyTo(toCurrency);
        }
    }

    @Override
    public void setAmount(String amount) {
        if (amount == null || !amount.equals("")) {
            uIdata.setAmount(amount);
        }
    }

    @Override
    public void convert() {
        if (!(uIdata.getCurrencyFrom() == null || uIdata.getAmount() == null || uIdata.getCurrencyTo() == null)) {
            view.showProgressBar();
            Observable<RateResponse> conversionRateFrom = fixerService.getConversionRates("c60b991b94ba2720c6f829d2a29e0135", uIdata.getCurrencyFrom());
            Observable<RateResponse> conversionRateTo = fixerService.getConversionRates("c60b991b94ba2720c6f829d2a29e0135", uIdata.getCurrencyTo());
            disposable = Observable.zip(conversionRateFrom, conversionRateTo, (fromRate, toRate) ->
                    toRate.getRates().getRate() / fromRate.getRates().getRate())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(conversionRate -> {
                        uIdata.setConvertedAmount(calculateConversion(conversionRate));
                        view.showConvertedAmount(uIdata.getConvertedAmount());
                        view.hideProgressbar();
                    }, error -> {
                        view.showErrorMessage();
                        view.hideProgressbar();
                    });
        }
    }

    private String calculateConversion(double rate) {
        return String.format("%.2f",(Double.valueOf(uIdata.getAmount()) * rate));
    }

    @Override
    public void dispose() {
        if(disposable!=null){
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }}
    }
}
