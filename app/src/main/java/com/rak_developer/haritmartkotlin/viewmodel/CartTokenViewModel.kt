package com.rak_developer.haritmartkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rak_developer.haritmartkotlin.model.CartTokenModel
import com.rak_developer.haritmartkotlin.model.InitModel
import com.rak_developer.haritmartkotlin.repository.CartTokenModelRepository
import com.rak_developer.haritmartkotlin.repository.InitModelRepository
import com.rak_developer.haritmartkotlin.util.ResponseGenerics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartTokenViewModel(val repository: CartTokenModelRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getData()
        }
    }

//    val quotesLiveDataList: LiveData<QuotesListModel>
//        get() = repository.quotesLiveData

    val liveData: LiveData<ResponseGenerics<CartTokenModel>>
        get() = repository.liveData

}