package com.rak_developer.haritmartkotlin.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    var BASE_URL = "https://dev.digiinterface.com/2020/grocery/webservices/"
    var IMG_BASE_URL = "https://dev.digiinterface.com/2020/grocery/"

    fun getInstance(): Retrofit {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Basic YWRtaW46MTIzNEBhZG1pbg==")
                .addHeader("x-api-key", "123456")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}