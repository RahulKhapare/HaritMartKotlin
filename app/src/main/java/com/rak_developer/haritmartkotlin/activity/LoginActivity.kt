package com.rak_developer.haritmartkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivityLoginBinding
import com.rak_developer.haritmartkotlin.util.*
import com.rak_developer.haritmartkotlin.viewmodel.LoginViewModel
import com.rak_developer.haritmartkotlin.viewmodelfactory.LoginModelFactory

class LoginActivity : AppCompatActivity() {

    val activity = this@LoginActivity;
    lateinit var binding: ActivityLoginBinding

    lateinit var loginViewModel: LoginViewModel


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

        binding.etxNumber.setText("8446929585")
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
//            loginVerificationCall(
//                binding.etxNumber.text.toString().trim(),
//                Config.CART_TOKEN,
//                Config.FCM_VALUE
//            )
            jumpOptVerification()
        }
    }


    override fun onResume() {
        super.onResume()
        if (Config.CAME_FROM.equals(Config.FROM_LOGIN)) {
            binding.txtTitle.text = "Welcome"
            binding.txtMessage.text = "Please login to your account"
        } else if (Config.CAME_FROM.equals(Config.FROM_REGISTER)) {
            binding.txtTitle.text = "Create Your Account"
            binding.txtMessage.text = "Please register with your number"
            binding.etxNumber.setText(Config.SIGN_UP_NUMBER)
        }
    }

    fun loginVerificationCall(mobile_number: String, cart_token: String, fcm_token: String) {
        val repository = (application as DataApplication).loginModelRepository
        loginViewModel = ViewModelProvider(
            this,
            LoginModelFactory(repository, mobile_number, cart_token, fcm_token)
        ).get(LoginViewModel::class.java)

        loginViewModel.liveData.observe(this, {
            //TODO Generic way to load data
            when (it) {
                is ResponseGenerics.Loading -> {
                }
                is ResponseGenerics.Success -> {
                    val resultData = it.data
                    Log.e("TAG", "loginCallData: " + resultData.toString())
                    resultData?.let {
                        val status = it.status
                        val err_code = it.err_code
                        val msg = it.msg
                        val data = it.data

                        try {
                            Log.e("TAG", "loginCallData_OTP: " + data.get(0).otp)
                        } catch (e: Exception) {
                            Log.e("TAG", "loginCallData_OTP: " + e.message)
                        }

                        if (data != null) {
//                            data?.forEach {
//                                Log.e("TAG", "loginCallData_OTP: " + it.otp)
//                            }
                        }
                    }

                }
                is ResponseGenerics.Error -> {
                }

            }

        })
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