package com.rak_developer.haritmartkotlin.repository

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rak_developer.haritmartkotlin.model.InitModel
import com.rak_developer.haritmartkotlin.network.APIService
import com.rak_developer.haritmartkotlin.network.RetrofitHelper
import com.rak_developer.haritmartkotlin.util.NetworkUtils
import com.rak_developer.haritmartkotlin.util.ResponseGenerics
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InitModelRepository(
    private val context: Context,
    private val service: APIService,
) {

    private val mutableLiveData = MutableLiveData<ResponseGenerics<InitModel>>()
    val liveData: LiveData<ResponseGenerics<InitModel>>
        get() = mutableLiveData

    suspend fun getData() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            try {
                GlobalScope.launch {
                    val result = service.getInitCall()
                    Log.e("TAG", "getDataInitCall: " + result.toString() )
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