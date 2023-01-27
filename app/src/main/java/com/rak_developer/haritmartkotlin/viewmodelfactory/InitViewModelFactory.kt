package com.rak_developer.haritmartkotlin.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rak_developer.haritmartkotlin.repository.InitModelRepository
import com.rak_developer.haritmartkotlin.viewmodel.InitViewModel

class InitViewModelFactory(private val repository: InitModelRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InitViewModel(repository) as T
    }
}