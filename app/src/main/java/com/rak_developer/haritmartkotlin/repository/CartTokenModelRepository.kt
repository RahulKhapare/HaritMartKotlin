package com.rak_developer.haritmartkotlin.repository

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rak_developer.haritmartkotlin.model.CartTokenModel
import com.rak_developer.haritmartkotlin.network.APIService
import com.rak_developer.haritmartkotlin.util.NetworkUtils
import com.rak_developer.haritmartkotlin.util.ResponseGenerics
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class CartTokenModelRepository(
    private val context: Context,
    private val service: APIService,
) {

    private val mutableLiveData = MutableLiveData<ResponseGenerics<CartTokenModel>>()
    val liveData: LiveData<ResponseGenerics<CartTokenModel>>
        get() = mutableLiveData

    suspend fun getData() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            try {
                GlobalScope.launch {
                    val result = service.getCartTokenCall("")
                    Log.e("TAG", "getData_Cart: " + result.body().toString())
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