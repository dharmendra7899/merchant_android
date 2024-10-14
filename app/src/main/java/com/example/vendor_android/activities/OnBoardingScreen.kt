package com.example.vendor_android.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.vendor_android.R
import com.example.vendor_android.adapters.OnboardingAdapter

class OnBoardingScreen : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var skipButton: Button
    private lateinit var nextButton: Button
    private lateinit var getStartedButton: Button
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var btnContainer: LinearLayout
    private lateinit var adapter: OnboardingAdapter

    private val layouts = listOf(
        R.layout.fragment_onboarding_first,
        R.layout.fragment_onboarding_second,
        R.layout.fragment_onboarding_third
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        viewPager = findViewById(R.id.viewPager)
        skipButton = findViewById(R.id.skip_button)
        nextButton = findViewById(R.id.next_button)
        getStartedButton = findViewById(R.id.get_started_button)
        indicatorContainer = findViewById(R.id.indicator_container)
        btnContainer = findViewById(R.id.btn)

        adapter = OnboardingAdapter(layouts)
        viewPager.adapter = adapter

        setupIndicators(layouts.size)
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
                if (position == layouts.size - 1) {
                    getStartedButton.visibility = View.VISIBLE
                    btnContainer.visibility = View.GONE
                } else {
                    getStartedButton.visibility = View.GONE
                    btnContainer.visibility = View.VISIBLE
                }
            }
        })


        skipButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        nextButton.setOnClickListener {
            if (viewPager.currentItem + 1 < layouts.size) {
                viewPager.currentItem += 1
            }
        }


        getStartedButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupIndicators(count: Int) {
        val indicators = arrayOfNulls<ImageView>(count)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(this)
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.indicator_inactive)
            )
            indicators[i]?.layoutParams = params
            indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.indicator_active)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.indicator_inactive)
                )
            }
        }
    }
}




