package com.example.vendor_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.vendor_android.R

class AddProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        var back = findViewById<ImageView>(R.id.back_btn_add)
        back.setOnClickListener(
            View.OnClickListener {
                finish()
            }
        )
    }
}