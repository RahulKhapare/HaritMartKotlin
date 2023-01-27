package com.rak_developer.haritmartkotlin.repository

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rak_developer.haritmartkotlin.model.InitModel
import com.rak_developer.haritmartkotlin.model.LoginModel
import com.rak_developer.haritmartkotlin.network.APIService
import com.rak_developer.haritmartkotlin.network.RetrofitHelper
import com.rak_developer.haritmartkotlin.util.NetworkUtils
import com.rak_developer.haritmartkotlin.util.ResponseGenerics
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginModelRepository(
    private val context: Context,
    private val service: APIService,
) {

    private val mutableLiveData = MutableLiveData<ResponseGenerics<LoginModel>>()
    val liveData: LiveData<ResponseGenerics<LoginModel>>
        get() = mutableLiveData

    suspend fun getData(mobile_number: String, cart_token: String, fcm_token: String) {
        Log.e("TAG", "loginCallData: " + mobile_number)
        if (NetworkUtils.isNetworkAvailable(context)) {
            try {
                GlobalScope.launch {
                    val result = service.getLoginCall(mobile_number, cart_token, "", fcm_token)
                    if (result.body() != null) {
                        mutableLiveData.postValue(ResponseGenerics.Success(result.body()))
                    } else {
                        mutableLiveData.postValue(ResponseGenerics.Error("No response data found.."))
                    }
                }
            } catch (e: Exception) {
                mutableLiveData.postValue(ResponseGenerics.Error(e.message.toString()))
            }
        } else {
            mutableLiveData.postValue(ResponseGenerics.Error("No internet connection available..."))
        }
    }
}