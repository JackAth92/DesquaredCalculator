package com.jackathan.desquaredcalculator.network;

import com.jackathan.desquaredcalculator.feature.converter.RateResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FixerService {
    @GET("latest")
    Observable<RateResponse> getConversionRates(@Query("access_key") String api, @Query("symbols") String symbol);
}
