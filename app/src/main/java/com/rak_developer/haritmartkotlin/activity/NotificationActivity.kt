package com.rak_developer.haritmartkotlin.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.NotificationAdapter
import com.rak_developer.haritmartkotlin.databinding.ActivityNotificationBinding
import com.rak_developer.haritmartkotlin.model.NotificationModel
import com.rak_developer.haritmartkotlin.util.WindowBar

class NotificationActivity : AppCompatActivity() {

    val activity = this@NotificationActivity;
    lateinit var binding: ActivityNotificationBinding

    val image: String =
        "https://img.freepik.com/free-vector/gradient-abstract-horizontal-sale-banner_52683-67806.jpg?size=626&ext=jpg";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        init()
    }

    fun init() {
        binding.toolbar.title = "Notifications"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        setNotificationData()
    }

    fun setNotificationData() {
        val model =
            NotificationModel("", "FLAT 50% off", "Over 250 + best sellers on deal today", image)
        val notificationModelList = ArrayList<NotificationModel>()
        notificationModelList.add(model)
        notificationModelList.add(model)
        notificationModelList.add(model)
        notificationModelList.add(model)
        notificationModelList.add(model)
        val adapter = NotificationAdapter(notificationModelList)
        binding!!.recyclerNotification.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerNotification.setHasFixedSize(true)
        binding!!.recyclerNotification.adapter = adapter
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