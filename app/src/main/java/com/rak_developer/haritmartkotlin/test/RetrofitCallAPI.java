package com.rak_developer.haritmartkotlin.test;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCallAPI {

    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();
    private static RetrofitAPIinterface retrofitAPIinterface = null;
    private static RetrofitAPIinterface retrofitRXAPIinterface = null;
    private static String base_url = " https://pspl-fast.hitachi-payments.com/FAST/";

    public static RetrofitAPIinterface getInstance(Context context) {

        Gson gson = new GsonBuilder()
                .setLenient() //building as lenient mode`enter code here`
                .create();

        //OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        // if (retrofitAPIinterface == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        retrofitAPIinterface = retrofit.create(RetrofitAPIinterface.class);
        // }


        return retrofitAPIinterface;
    }

    public static RetrofitAPIinterface getRxInstance() {
        Gson gson = new GsonBuilder()
                .setLenient() //building as lenient mode`enter code here`
                .create();

        //OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();


        if (retrofitRXAPIinterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            retrofitRXAPIinterface = retrofit.create(RetrofitAPIinterface.class);
        }


        return retrofitRXAPIinterface;
    }


}



