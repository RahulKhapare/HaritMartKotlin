package com.rak_developer.haritmartkotlin.network

import com.rak_developer.haritmartkotlin.model.InitModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIService {

    //we can pass everytime header, otherwise from retrofit helper class
//    @Headers(
//        "Authorization: Basic YWRtaW46MTIzNEBhZG1pbg==",
//        "x-api-key: 123456",
//        "Content-Type: application/json"
//    )
    @GET("init")
    suspend fun getInitCall(): Response<InitModel>

}