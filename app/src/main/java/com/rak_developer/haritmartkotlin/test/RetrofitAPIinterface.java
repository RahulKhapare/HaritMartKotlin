package com.rak_developer.haritmartkotlin.test;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RetrofitAPIinterface {
    @POST("API/GetAllTravelTypes")
    Call<TravelTypeResponse> getTravelTypes();
}
