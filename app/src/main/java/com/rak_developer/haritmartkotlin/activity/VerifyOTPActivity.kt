package com.rak_developer.haritmartkotlin.activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivityVerifyOtpactivityBinding
import com.rak_developer.haritmartkotlin.util.Click
import com.rak_developer.haritmartkotlin.util.Config
import com.rak_developer.haritmartkotlin.util.Toast
import com.rak_developer.haritmartkotlin.util.WindowBar

class VerifyOTPActivity : AppCompatActivity() {

    val activity = this@VerifyOTPActivity;
    lateinit var binding: ActivityVerifyOtpactivityBinding

    private var loginNumber: String? = null
    private var loginOTP: String? = null

    private var resetText: String? = null
    private var timer: CountDownTimer? = null

    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otpactivity);
        init()
    }

    fun init() {
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        progressDialog = ProgressDialog(activity)

        resetText = "Resend OTP"

        loginNumber = intent.getStringExtra(Config.LOGIN_NUMBER)
        loginOTP = intent.getStringExtra(Config.LOGIN_OTP)

        binding.txtInfo.text = "Please enter the OTP sent to $loginNumber"
        binding.etxOtp.setText(loginOTP)

        binding.txtTimer.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        onClick()
        startTimer()
    }

    fun onClick() {

        binding.btnProcess.setOnClickListener {
            checkValidation()
        }

        binding.txtTimer.setOnClickListener { v ->
            Click.preventTwoClick(v)
            if (binding.txtTimer.text.toString().equals(resetText)) {
                showProgress()
                Handler().postDelayed({
                    hideProgress()
                    Toast.showMessage(activity, "OTP send successfully")
                    binding.etxOtp.setText(loginOTP)
                    startTimer()
                }, 1230)
            }
        }

    }

    private fun startTimer() {
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.txtTimer.text = "00 : " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                binding.txtTimer.text = resetText
                binding.etxOtp.setText("")
            }
        }.start()
    }

    private fun closeTimer() {
        try {
            if (timer != null) {
                timer!!.cancel()
            }
        } catch (e: Exception) {
        }
    }

    private fun checkValidation() {
        if (TextUtils.isEmpty(binding.etxOtp.text.toString())) {
            Toast.showMessage(activity, "Enter any OTP")
        } else if (binding.etxOtp.text.toString().length < 6) {
            Toast.showMessage(activity, "Enter 6 digit OTP")
        } else {
            jumpToLocation()
        }
    }

    fun jumpToLocation() {
        closeTimer()
        intent =
            Intent(activity, SelectLocationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        closeTimer()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return false
    }

    private fun showProgress() {
        progressDialog?.setMessage("Please wait....")
        progressDialog?.show()
    }

    private fun hideProgress() {
        progressDialog?.hide()
    }
}