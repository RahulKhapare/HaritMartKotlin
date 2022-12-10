package com.rak_developer.haritmartkotlin.model

data class LoginModel(
    val `data`: DataX,
    val err_code: Int,
    val msg: String,
    val status: Int
)