package com.rak_developer.haritmartkotlin.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rak_developer.haritmartkotlin.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    private TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button btnTest = findViewById(R.id.btnTest);
        txtResponse = findViewById(R.id.txtResponse);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAPICall();
            }
        });
    }

    void checkAPICall() {
        Call<TravelTypeResponse> callSiteList = RetrofitCallAPI.getInstance(TestActivity.this).getTravelTypes();
        callSiteList.enqueue(new Callback<TravelTypeResponse>() {
            @Override
            public void onResponse(Call<TravelTypeResponse> call, Response<TravelTypeResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    String responseData = "OnResponse : \n\n" + response;
                    Log.e("TAG", responseData);
                    setData(responseData);
                } else {
                    String responseNull = "OnResponse : \n\n" + "Null Response...";
                    Log.e("TAG", responseNull);
                    setData(responseNull);
                }
            }

            @Override
            public void onFailure(Call<TravelTypeResponse> call, Throwable t) {
                String errorMessage = "OnResponseError : \n\n" + t.getMessage();
                Log.e("TAG", errorMessage);
                setData(errorMessage);
            }
        });
    }

    void setData(String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResponse.setText(response);
            }
        });
    }
}