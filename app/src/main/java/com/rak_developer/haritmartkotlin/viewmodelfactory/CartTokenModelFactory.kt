package com.rak_developer.haritmartkotlin.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rak_developer.haritmartkotlin.repository.CartTokenModelRepository
import com.rak_developer.haritmartkotlin.repository.InitModelRepository
import com.rak_developer.haritmartkotlin.viewmodel.CartTokenViewModel
import com.rak_developer.haritmartkotlin.viewmodel.InitViewModel

class CartTokenModelFactory(private val repository: CartTokenModelRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartTokenViewModel(repository) as T
    }
}