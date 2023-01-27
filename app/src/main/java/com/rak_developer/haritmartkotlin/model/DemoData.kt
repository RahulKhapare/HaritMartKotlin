package com.rak_developer.haritmartkotlin.model

import com.google.gson.annotations.SerializedName

class DemoData {

    @SerializedName("cart_token")
    private val cart_token: String? = null
    override fun toString(): String {
        return "DemoData(cart_token=$cart_token)"
    }

}