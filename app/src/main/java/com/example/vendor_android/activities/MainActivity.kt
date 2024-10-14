package com.example.vendor_android.activities

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.ProductFragment
import com.example.vendor_android.R
import com.example.vendor_android.fragments.AccountFragment
import com.example.vendor_android.fragments.ChatFragment
import com.example.vendor_android.fragments.HomeFragment
import com.example.vendor_android.fragments.OrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_product -> selectedFragment = ProductFragment()
                R.id.nav_order -> selectedFragment = OrderFragment()
                R.id.nav_chat -> selectedFragment = ChatFragment()
                R.id.nav_account -> selectedFragment = AccountFragment()
            }
            loadFragment(selectedFragment)
        }




    }



    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment is HomeFragment) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Double tao to exit", Toast.LENGTH_SHORT).show()


            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else {

            loadFragment(HomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }
}