package com.rak_developer.haritmartkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.OnBoardAdapter
import com.rak_developer.haritmartkotlin.databinding.ActivityOnBoardBinding
import com.rak_developer.haritmartkotlin.model.OnBoardModel
import com.rak_developer.haritmartkotlin.util.WindowBar

class OnBoardActivity : AppCompatActivity() {

    val activity = this@OnBoardActivity;
    lateinit var binding: ActivityOnBoardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_board);
        init()
    }

    fun init() {
        setupOnBoardingItems()
        binding.btnLogin.setOnClickListener {
            intent =
                Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSecondary.setOnClickListener {
            intent =
                Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.txtSkip.setOnClickListener {
            intent =
                Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        binding.imgFacebook.setOnClickListener {

        }
        binding.imgGoogle.setOnClickListener {

        }
    }

    fun setupOnBoardingItems() {
        val sliderModelList: MutableList<OnBoardModel> = ArrayList<OnBoardModel>()
        sliderModelList.add(
            OnBoardModel(
                resources.getDrawable(R.drawable.ic_group_4172),
                "Fast Doorstep Deliveries"
            )
        )
        sliderModelList.add(
            OnBoardModel(
                resources.getDrawable(R.drawable.ic_group_4173),
                "Select ipsum dolor sit amet"
            )
        )
        sliderModelList.add(
            OnBoardModel(
                resources.getDrawable(R.drawable.ic_group_145),
                "Select ipsum dolor sit amet"
            )
        )
        val onBoardingAdapter = OnBoardAdapter(activity, sliderModelList)
        binding.viewPager.adapter = onBoardingAdapter
        binding.tabDots.setupWithViewPager(binding.viewPager, true)
        autoSlider(onBoardingAdapter)
    }

    fun autoSlider(adapter: OnBoardAdapter) {
        try {
            val handler = Handler()
            var runnable: Runnable? = null
            if (runnable != null) handler.removeCallbacks(runnable)
            runnable = object : Runnable {
                override fun run() {
                    handler.postDelayed(this, 3000)
                    if (binding.viewPager.getCurrentItem() === adapter.getCount() - 1) binding.viewPager.setCurrentItem(
                        0
                    ) else binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1)
                }
            }
            runnable.run()
        } catch (e: Exception) {

        }
    }
}