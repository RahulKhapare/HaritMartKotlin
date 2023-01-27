package com.rak_developer.haritmartkotlin.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.ArrayMap
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivitySplashBinding
import com.rak_developer.haritmartkotlin.model.DemoTokenModel
import com.rak_developer.haritmartkotlin.model.LoginModel
import com.rak_developer.haritmartkotlin.network.APIService
import com.rak_developer.haritmartkotlin.network.RetrofitHelper
import com.rak_developer.haritmartkotlin.util.Config
import com.rak_developer.haritmartkotlin.util.DataApplication
import com.rak_developer.haritmartkotlin.util.ResponseGenerics
import com.rak_developer.haritmartkotlin.util.WindowBar
import com.rak_developer.haritmartkotlin.viewmodel.CartTokenViewModel
import com.rak_developer.haritmartkotlin.viewmodel.InitViewModel
import com.rak_developer.haritmartkotlin.viewmodelfactory.CartTokenModelFactory
import com.rak_developer.haritmartkotlin.viewmodelfactory.InitViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashActivity : AppCompatActivity() {

    val activity = this@SplashActivity
    lateinit var binding: ActivitySplashBinding

    lateinit var quotesViewModel: InitViewModel
    lateinit var cartTokenViewModel: CartTokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        init()
    }

    fun init() {
//        initAPICall()
//        loginAPICall()
//        getLoginCallExtra()

//        initCall()
//        cartTokenCall()
        jumpToOnBoard()
    }

    fun initCall() {
        val repository = (application as DataApplication).initRepository
        quotesViewModel = ViewModelProvider(
            this,
            InitViewModelFactory(repository)
        ).get(InitViewModel::class.java)

        quotesViewModel.liveData.observe(this, {
            //TODO Normal way to load data
//            val result = it.data
//            if (result != null) {
//                val status = result.status
//                val err_code = result.err_code
//                val msg = result.msg
//
//                val data = result.data
//                val min_version = data.min_version
//                val flash_screen_list = data.flash_screen_list
//                val current_version = data.current_version
//                val ios_min_version = data.ios_min_version
//                val ios_current_version = data.ios_current_version
//                val flash_screen_image_path = data.flash_screen_image_path
//                Log.e("TAG", "initCallData_flash_screen_image_path: " + flash_screen_image_path)
//                flash_screen_list?.forEach {
////                    Log.e("TAG", "quotesAPI_Author: " + it.toString())
//                }
//            }

            //TODO Generic way to load data
            when (it) {
                is ResponseGenerics.Loading -> {
                }
                is ResponseGenerics.Success -> {
                    val resultData = it.data
                    Log.e("TAG", "initCallAS: " + resultData.toString())
                    resultData?.let {
                        val status = it.status
                        val err_code = it.err_code
                        val msg = it.msg
                        val data = it.data
                        if (data != null) {
                            val min_version = data.min_version
                            val flash_screen_list = data.flash_screen_list
                            val current_version = data.current_version
                            val ios_min_version = data.ios_min_version
                            val ios_current_version = data.ios_current_version
                            val flash_screen_image_path = data.flash_screen_image_path
                            flash_screen_list?.forEach {
//                    Log.e("TAG", "quotesAPI_Author: " + it.toString())
                            }
                            jumpToOnBoard()
                            Log.e("TAG", "flash_screen_image_path: " + flash_screen_image_path)
                        }
                    }

                }
                is ResponseGenerics.Error -> {
                }

            }

        })
    }

    fun cartTokenCall() {
        val repository = (application as DataApplication).cartTokenRepository
        cartTokenViewModel = ViewModelProvider(
            this,
            CartTokenModelFactory(repository)
        ).get(CartTokenViewModel::class.java)

        cartTokenViewModel.liveData.observe(this, {
            var responseData = it.toString()
            Log.e("TAG", "cartTokenCallData: " + responseData)
        })
    }

    fun initAPICall() {
        val apiCall = RetrofitHelper.getInstance(activity).create(APIService::class.java)
        GlobalScope.launch {
            val result = apiCall.getInitCall()
            if (result != null) {
                Log.e("TAG", "APIResponseInit: " + result.body().toString())
                val initModel = result.body()
                if (initModel != null) {
                    Log.e("TAG", "versionCode: " + initModel.err_code)
                    jumpToOnBoard()
                } else {
                    Log.e("TAG", "APIResponseInit: " + "Null...")
                }
            }
        }
    }

    fun tokenAPICall() {
        val apiCall = RetrofitHelper.getInstance(activity).create(APIService::class.java)
        GlobalScope.launch {
            val jsonObject = JSONObject()
            val jsonParams: Map<String, Any> = ArrayMap()
            val body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                JSONObject(jsonParams).toString()
            )
            val result = apiCall.getCartTokenCall(Config.CART_TOKEN)
            if (result != null) {
                Log.e("TAG", "APIResponseToken: " + result.body().toString())
                val initModel = result.toString()
                if (initModel != null) {
//                    jumpToOnBoard()
                }
            } else {
                Log.e("TAG", "APIResponseToken: " + "Null...")
            }
        }
    }


    fun getLoginCallExtra() {
        val modelCall: Call<LoginModel?>? = RetrofitHelper.getInstanceExtra(activity)
            ?.getLoginCallExtra("8446929585", Config.CART_TOKEN, "123456", Config.FCM_VALUE)
        modelCall?.enqueue(object : Callback<LoginModel?> {
            override fun onResponse(
                call: Call<LoginModel?>,
                response: Response<LoginModel?>
            ) {
                if (response.body() != null) {
                    Log.e("TAG", "APIResponseLoginExtra: " + response.body().toString())
                } else {
                    Log.e("TAG", "APIResponseLoginExtra: " + "Null Data")
                }
            }

            override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                Log.e("TAG", "APIResponseLoginExtra: " + "Error - " + t.message)
            }
        })

    }

    fun getTokenCallExtra() {

        val modelCall: Call<DemoTokenModel?>? = RetrofitHelper.getInstanceExtra(activity)
            ?.getTokenCallExtra(Config.CART_TOKEN)
        modelCall?.enqueue(object : Callback<DemoTokenModel?> {
            override fun onResponse(
                call: Call<DemoTokenModel?>,
                response: Response<DemoTokenModel?>
            ) {
                if (response.body() != null) {
                    Log.e("TAG", "APIResponseTokenExtra: " + response.body().toString())
                } else {
                    Log.e("TAG", "APIResponseTokenExtra: " + "Null Data")
                }
            }

            override fun onFailure(call: Call<DemoTokenModel?>, t: Throwable) {
                Log.e("TAG", "APIResponseTokenExtra: " + "Error - " + t.message)
            }
        })

    }

    fun jumpToOnBoard() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            intent =
                Intent(activity, OnBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    fun jumpToLocation() {
        intent =
            Intent(activity, SelectLocationActivity::class.java)
        startActivity(intent)
    }


}