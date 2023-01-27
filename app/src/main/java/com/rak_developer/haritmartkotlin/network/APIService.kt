package com.rak_developer.haritmartkotlin.network

import com.rak_developer.haritmartkotlin.model.CartTokenModel
import com.rak_developer.haritmartkotlin.model.DemoTokenModel
import com.rak_developer.haritmartkotlin.model.InitModel
import com.rak_developer.haritmartkotlin.model.LoginModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface APIService {

    //    we can pass everytime header, otherwise from retrofit helper class
//    @Headers(
//        "Authorization: Basic YWRtaW46MTIzNEBhZG1pbg==",
//        "x-api-key: 123456",
//        "Content-Type: application/json"
//    )

    @GET("init")
    suspend fun getInitCall(): Response<InitModel>

    @FormUrlEncoded
    @POST("cart_token")
    suspend fun getCartTokenCall(
        @Field("cart_token") cart_token: String?
    ): Response<CartTokenModel>


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

    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic YWRtaW46MTIzNEBhZG1pbg==",
        "x-api-key: 123456"
    )
    @FormUrlEncoded
    @POST("login_with_otp")
    fun getLoginCallExtra(
        @Field("phone") phone: String?,
        @Field("cart_token") cart_token: String?,
        @Field("otp") otp: String?,
        @Field("fcm_value") fcm_value: String?
    ): Call<LoginModel?>?

    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic YWRtaW46MTIzNEBhZG1pbg==",
        "x-api-key: 123456"
    )
    @FormUrlEncoded
    @POST("cart_token")
    fun getTokenCallExtra(
        @Field("cart_token") cart_token: String?
    ): Call<DemoTokenModel?>?

}