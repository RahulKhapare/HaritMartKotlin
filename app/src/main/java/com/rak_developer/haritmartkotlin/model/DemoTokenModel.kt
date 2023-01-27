package com.rak_developer.haritmartkotlin.model

import com.google.gson.annotations.SerializedName

class DemoTokenModel {

    @SerializedName("err_code")
    private val err_code: Int? = null

    @SerializedName("msg")
    private val msg: String? = null

    @SerializedName("status")
    private val status: Int? = null

    @SerializedName("data")
    private val data: DemoData? = null
    override fun toString(): String {
        return "DemoTokenModel(err_code=$err_code, msg=$msg, status=$status, data=$data)"
    }


}