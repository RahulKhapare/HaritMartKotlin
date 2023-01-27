package com.rak_developer.haritmartkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.NotificationAdapter
import com.rak_developer.haritmartkotlin.databinding.ActivityMyAccountBinding
import com.rak_developer.haritmartkotlin.databinding.ActivityNotificationBinding
import com.rak_developer.haritmartkotlin.model.NotificationModel
import com.rak_developer.haritmartkotlin.util.WindowBar

class MyAccountActivity : AppCompatActivity() {

    val activity = this@MyAccountActivity;
    lateinit var binding: ActivityMyAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_account);
        init()
    }

    fun init() {
        binding.toolbar.title = "My Account"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        onClick()
    }

    fun onClick() {
        binding.imgEdit.setOnClickListener {
            intent =
                Intent(activity, UserProfileActivity::class.java)
            startActivity(intent)
        }
        binding.lnrImage.setOnClickListener {

        }
        binding.lnrChangePass.setOnClickListener {

        }
        binding.lnrMyOrder.setOnClickListener {
            intent =
                Intent(activity, MyOrderActivity::class.java)
            startActivity(intent)
        }
        binding.lnrWallet.setOnClickListener {
            intent =
                Intent(activity, MyWalletActivity::class.java)
            startActivity(intent)
        }
        binding.lnrMyPayment.setOnClickListener {

        }
        binding.lnrMyRating.setOnClickListener {

        }
        binding.lnrNotifications.setOnClickListener {

        }
        binding.lnrGiftCard.setOnClickListener {

        }
        binding.lnrMyAddress.setOnClickListener {

        }
        binding.lnrCustomerService.setOnClickListener {

        }
        binding.lnrTermCondition.setOnClickListener {

        }
        binding.lnrLogOut.setOnClickListener {

        }
        binding.txtChange.setOnClickListener {

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}