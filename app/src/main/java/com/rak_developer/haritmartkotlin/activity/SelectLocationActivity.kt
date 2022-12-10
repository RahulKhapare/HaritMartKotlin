package com.rak_developer.haritmartkotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivitySelectLocationBinding
import com.rak_developer.haritmartkotlin.util.WindowBar

class SelectLocationActivity : AppCompatActivity() {

    val activity = this@SelectLocationActivity;
    lateinit var binding: ActivitySelectLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_location);
        init()
    }

    fun init() {
        binding.cardCurrentLocation.setOnClickListener {

        }
        binding.btnLocation.setOnClickListener {
            jumpToMain()
        }
    }


    fun jumpToMain() {
        intent =
            Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}