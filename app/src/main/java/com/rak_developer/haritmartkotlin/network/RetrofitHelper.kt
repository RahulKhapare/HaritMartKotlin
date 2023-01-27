package com.rak_developer.haritmartkotlin.network

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


object RetrofitHelper {

    var BASE_URL = "https://dev.digiinterface.com/2020/grocery/webservices/"
    var IMG_BASE_URL = "https://dev.digiinterface.com/2020/grocery/"

    var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.MINUTES)
        .writeTimeout(30, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private var retrofitAPIService: APIService? = null

    fun getInstanceExtra(context: Context?): APIService? {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callbackExecutor(Executors.newSingleThreadExecutor())
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        retrofitAPIService = retrofit.create(
            APIService::class.java
        )
        return retrofitAPIService
    }

    fun getInstance(context: Context?): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Basic YWRtaW46MTIzNEBhZG1pbg==")
                    .addHeader("x-api-key", "123456")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }.build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callbackExecutor(Executors.newSingleThreadExecutor())
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
}