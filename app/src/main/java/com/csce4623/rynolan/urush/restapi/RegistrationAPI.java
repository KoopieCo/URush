package com.csce4623.rynolan.urush.restapi;

import com.csce4623.rynolan.urush.models.RusheeInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationAPI {
    @POST("/registration")
    Call<RusheeInfo> addRushee(@Body RusheeInfo info);
}
