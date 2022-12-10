package com.rak_developer.haritmartkotlin.network

import com.rak_developer.haritmartkotlin.model.InitModel
import com.rak_developer.haritmartkotlin.model.LoginModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface APIService {

    //we can pass everytime header, otherwise from retrofit helper class
//    @Headers(
//        "Authorization: Basic YWRtaW46MTIzNEBhZG1pbg==",
//        "x-api-key: 123456",
//        "Content-Type: application/json"
//    )
    @GET("init")
    suspend fun getInitCall(): Response<InitModel>

    @FormUrlEncoded
    @POST("login_with_otp")
    suspend fun getLoginCall(
        @Field("phone") phone: String?,
        @Field("cart_token") cart_token: String?,
        @Field("otp") otp: String?,
        @Field("fcm_value") fcm_value: String?
    ): Response<LoginModel>

    @FormUrlEncoded
    @POST("login_with_otp")
    fun getUserLogin(
        @Field("phone") phone: String?,
        @Field("cart_token") cart_token: String?,
        @Field("otp") otp: String?,
        @Field("fcm_value") fcm_value: String?
    ): Call<String?>?
}