package com.rak_developer.haritmartkotlin.util

import android.app.Application
import com.rak_developer.haritmartkotlin.network.APIService
import com.rak_developer.haritmartkotlin.network.RetrofitHelper
import com.rak_developer.haritmartkotlin.repository.CartTokenModelRepository
import com.rak_developer.haritmartkotlin.repository.InitModelRepository
import com.rak_developer.haritmartkotlin.repository.LoginModelRepository
import dagger.hilt.android.HiltAndroidApp

// this annotation for paging
@HiltAndroidApp
class DataApplication : Application() {

    lateinit var initRepository: InitModelRepository
    lateinit var cartTokenRepository: CartTokenModelRepository
    lateinit var loginModelRepository: LoginModelRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    fun initialize() {
        val initService =
            RetrofitHelper.getInstance(applicationContext).create(APIService::class.java)
        initRepository = InitModelRepository(applicationContext, initService)
        cartTokenRepository = CartTokenModelRepository(applicationContext, initService)
        loginModelRepository = LoginModelRepository(applicationContext, initService)
    }
}