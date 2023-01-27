package com.rak_developer.haritmartkotlin.model

data class LoginModel(
    val `data`: List<DataLogin>,
    val err_code: Int,
    val msg: String,
    val status: Int
)