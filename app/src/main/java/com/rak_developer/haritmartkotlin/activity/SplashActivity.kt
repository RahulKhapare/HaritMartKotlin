package com.rak_developer.haritmartkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivitySplashBinding
import com.rak_developer.haritmartkotlin.network.APIService
import com.rak_developer.haritmartkotlin.network.RetrofitHelper
import com.rak_developer.haritmartkotlin.util.WindowBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    val activity = this@SplashActivity;
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        init()
    }

    fun init() {
        loginAPICall()
    }

    fun loginAPICall() {
        val initAPI = RetrofitHelper.getInstance().create(APIService::class.java)
        GlobalScope.launch {
            val result = initAPI.getInitCall()
            if (result != null) {
                Log.e("TAG", "initAPIResponse: " + result.body().toString())
                val initModel = result.body()
                if (initModel != null) {
                    Log.e("TAG", "versionCode: " + initModel.err_code)
                }
            }
        }
    }
}