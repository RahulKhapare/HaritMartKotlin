package com.rak_developer.haritmartkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rak_developer.haritmartkotlin.model.InitModel
import com.rak_developer.haritmartkotlin.model.LoginModel
import com.rak_developer.haritmartkotlin.repository.InitModelRepository
import com.rak_developer.haritmartkotlin.repository.LoginModelRepository
import com.rak_developer.haritmartkotlin.util.ResponseGenerics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    val repository: LoginModelRepository,
    val mobile_number: String,
    val cart_token: String,
    val fcm_token: String
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getData(mobile_number,cart_token,fcm_token)
        }
    }

//    val quotesLiveDataList: LiveData<QuotesListModel>
//        get() = repository.quotesLiveData

    val liveData: LiveData<ResponseGenerics<LoginModel>>
        get() = repository.liveData

}