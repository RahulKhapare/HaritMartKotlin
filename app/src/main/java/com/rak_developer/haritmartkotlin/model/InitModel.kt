package com.rak_developer.haritmartkotlin.model

data class InitModel(
    val `data`: DataInit,
    val err_code: Int,
    val msg: String,
    val status: Int
)