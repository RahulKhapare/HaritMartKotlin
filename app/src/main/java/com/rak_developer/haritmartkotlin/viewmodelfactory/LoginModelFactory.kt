package com.rak_developer.haritmartkotlin.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rak_developer.haritmartkotlin.repository.CartTokenModelRepository
import com.rak_developer.haritmartkotlin.repository.InitModelRepository
import com.rak_developer.haritmartkotlin.repository.LoginModelRepository
import com.rak_developer.haritmartkotlin.viewmodel.CartTokenViewModel
import com.rak_developer.haritmartkotlin.viewmodel.InitViewModel
import com.rak_developer.haritmartkotlin.viewmodel.LoginViewModel

class LoginModelFactory(
    private val repository: LoginModelRepository,
    private val mobile_number: String,
    private val cart_token: String,
    private val fcm_token: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository,mobile_number,cart_token,fcm_token) as T
    }
}