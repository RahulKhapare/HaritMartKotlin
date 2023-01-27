package com.rak_developer.haritmartkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivityRegisterBinding
import com.rak_developer.haritmartkotlin.util.Config
import com.rak_developer.haritmartkotlin.util.Toast
import com.rak_developer.haritmartkotlin.util.Validation
import com.rak_developer.haritmartkotlin.util.WindowBar

class RegisterActivity : AppCompatActivity() {

    val activity = this@RegisterActivity;
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        init()
    }

    fun init() {
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        onClick()
    }

    fun onClick() {
        binding.imgPass.setOnClickListener { v ->
            if (binding.etxPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                (v as ImageView).setImageResource(R.drawable.ic_baseline_on_eye_24)
                //Show Password
                binding.etxPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                (v as ImageView).setImageResource(R.drawable.ic_baseline_off_eye_24)
                //Hide Password
                binding.etxPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
            binding.etxPassword.setSelection(binding.etxPassword.text.toString().length)
        }

        binding.imgConPass.setOnClickListener { v ->
            if (binding.etxConPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                (v as ImageView).setImageResource(R.drawable.ic_baseline_on_eye_24)
                //Show Password
                binding.etxConPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                (v as ImageView).setImageResource(R.drawable.ic_baseline_off_eye_24)
                //Hide Password
                binding.etxConPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
            binding.etxConPassword.setSelection(binding.etxConPassword.text.toString().length)
        }

        binding.btnProcess.setOnClickListener {
            checkValidation()
        }
    }

    fun checkValidation() {
        if (TextUtils.isEmpty(binding.etxName.getText().toString().trim())) {
            Toast.showMessage(activity, "Enter full name")
        } else if (TextUtils.isEmpty(binding.etxEmail.getText().toString().trim())) {
            Toast.showMessage(activity, "Enter email id")
        } else if (!Validation.validEmail(binding.etxEmail.getText().toString().trim())) {
            Toast.showMessage(activity, "Enter valid email id")
        } else if (TextUtils.isEmpty(binding.etxNumber.text.toString())) {
            Toast.showMessage(activity, "Enter mobile number")
        } else if (binding.etxNumber.text.toString()
                .length > 10 || binding.etxNumber.text.toString().length < 10
        ) {
            Toast.showMessage(activity, "Enter 10 digit mobile number")
        } else if (TextUtils.isEmpty(binding.etxPassword.getText().toString().trim())) {
            Toast.showMessage(activity, "Enter password")
        } else if (binding.etxPassword.getText().toString().length < 6) {
            Toast.showMessage(activity, "Enter minimum 6 digit password")
        } else if (TextUtils.isEmpty(binding.etxConPassword.getText().toString().trim())) {
            Toast.showMessage(activity, "Enter confirm password")
        } else if (!binding.etxConPassword.getText().toString().trim()
                .equals(binding.etxPassword.getText().toString().trim())
        ) {
            Toast.showMessage(activity, "Confirm password not matched")
        } else {
            jumpToVerification();
        }
    }

    fun jumpToVerification() {
        Config.SIGN_UP_NUMBER = binding.etxNumber.text.toString().trim()
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return false
    }


}