package com.rak_developer.haritmartkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivityMainBinding
import com.rak_developer.haritmartkotlin.fragment.CartFragment
import com.rak_developer.haritmartkotlin.fragment.FavouriteFragment
import com.rak_developer.haritmartkotlin.fragment.HomeFragment
import com.rak_developer.haritmartkotlin.fragment.SearchFragment
import com.rak_developer.haritmartkotlin.util.Config
import com.rak_developer.haritmartkotlin.util.Toast
import com.rak_developer.haritmartkotlin.util.WindowBar


class MainActivity : AppCompatActivity() {

    val activity = this@MainActivity;
    lateinit var binding: ActivityMainBinding
    val home: String? = "Home"
    val search: String? = "Search"
    val cart: String? = "Cart"
    val favorite: String? = "Favorite"

    private val TIME_DELAY = 2000
    private var back_pressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init()
    }

    fun init() {
        binding.txtAddress.text = Config.LOCATION
        home?.let { loadFragment(HomeFragment(), it) }
        setupBottomNav()
    }

    fun setupBottomNav() {
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    home?.let { it1 -> loadFragment(HomeFragment(), it1) }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.seacrh -> {
                    search?.let { it1 -> loadFragment(SearchFragment(), it1) }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.cart -> {
                    cart?.let { it1 -> loadFragment(CartFragment(), it1) }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    favorite?.let { it1 -> loadFragment(FavouriteFragment(), it1) }
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    home?.let { it1 -> loadFragment(HomeFragment(), it1) }
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment, value: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(value)
        transaction.commit()
    }

    override fun onBackPressed() {
        var selectedId = binding.bottomNav.getSelectedItemId()
        if (selectedId == R.id.home) {
            finishAction();
        } else {
            binding.bottomNav.setSelectedItemId(R.id.home);
        }
    }

    fun handleBottomNavigation() {
        var selectedId = binding.bottomNav.getSelectedItemId()
        when (selectedId) {
            R.id.home -> {
                Toast.showMessage(activity, "Exit")
            }
            R.id.seacrh -> {
                binding.bottomNav.setSelectedItemId(R.id.home);
            }
            R.id.cart -> {
                binding.bottomNav.setSelectedItemId(R.id.seacrh);
            }
            R.id.favorite -> {
                binding.bottomNav.setSelectedItemId(R.id.cart);
            }

        }
    }

    private fun finishAction() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            finishAffinity()
            finish()
        } else {
            Toast.showMessage(activity, "Press once again to exit!")
        }
        back_pressed = System.currentTimeMillis()
    }
}