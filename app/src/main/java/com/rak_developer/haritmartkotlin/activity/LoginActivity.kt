package com.rak_developer.haritmartkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivityLoginBinding
import com.rak_developer.haritmartkotlin.network.APIService
import com.rak_developer.haritmartkotlin.network.RetrofitHelper
import com.rak_developer.haritmartkotlin.util.Config
import com.rak_developer.haritmartkotlin.util.Toast
import com.rak_developer.haritmartkotlin.util.WindowBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    val activity = this@LoginActivity;
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        init()
    }

    fun init() {
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        binding.btnProcess.setOnClickListener {
            checkValidation()
        }
    }

    fun checkValidation() {
        if (TextUtils.isEmpty(binding.etxNumber.text.toString())) {
            Toast.showMessage(activity, "Enter mobile number")
        } else if (binding.etxNumber.text.toString()
                .length > 10 || binding.etxNumber.text.toString().length < 10
        ) {
            Toast.showMessage(activity, "Enter 10 digit mobile number")
        } else {
            jumpOptVerification()
        }
    }

    fun loginAPICall() {
        val apiCall = RetrofitHelper.getInstance().create(APIService::class.java)
        GlobalScope.launch {
            val result = apiCall.getUserLogin(
                binding.etxNumber.text.toString(),
                Config.CART_TOKEN,
                "",
                Config.FCM_VALUE
            )
            if (result != null) {
                Log.e("TAG", "apiResponse: " + result)
                val model = result.toString()
                if (model != null) {
//                    Log.e("TAG", "apiResponse1212: " + model.)
                }
            }
        }
    }

    fun jumpOptVerification() {
        intent =
            Intent(activity, VerifyOTPActivity::class.java)
        intent.putExtra(Config.LOGIN_NUMBER, binding.etxNumber.text.toString())
        intent.putExtra(Config.LOGIN_OTP, Config.DUMMY_OPT)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return false
    }

}