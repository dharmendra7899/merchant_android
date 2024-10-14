package com.example.vendor_android.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vendor_android.R
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var merchantLayout: LinearLayout
    private lateinit var aggregatorLayout: LinearLayout
    private lateinit var btnContinue: Button
    private lateinit var notUser: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var mobileNumber: TextInputEditText
    private lateinit var password: TextInputEditText
    private var selectedUserType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        merchantLayout = findViewById(R.id.btn_merchant)
        aggregatorLayout = findViewById(R.id.btn_aggregator)
        btnContinue = findViewById(R.id.btn_continue)
        mobileNumber = findViewById(R.id.mobile_number)
        password = findViewById(R.id.password)
        notUser = findViewById(R.id.register_text)
        checkBox = findViewById(R.id.agree_checkbox)

        merchantLayout.setOnClickListener {
            selectUserType("Merchant")
        }

        aggregatorLayout.setOnClickListener {
            selectUserType("Aggregator")
        }

        notUser.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnContinue.setOnClickListener {
            if (validateInputs()) {
                showLoader(true)
                Handler(Looper.getMainLooper()).postDelayed({
                    showLoader(false)
                    navigateToNextScreen()
                }, 1000) // Simulate delay
            }
        }
    }

    private fun selectUserType(type: String) {
        selectedUserType = type
        merchantLayout.isSelected = type == "Merchant"
        aggregatorLayout.isSelected = type == "Aggregator"
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        val mobile = mobileNumber.text.toString().trim()
        val pwd = password.text.toString().trim()

        if (mobile.isEmpty()) {
            mobileNumber.error = "Mobile number is required"
            isValid = false
        } else if (mobile.length != 10) {
            mobileNumber.error = "Enter a valid 10-digit mobile number"
            isValid = false
        }

        if (pwd.isEmpty()) {
            password.error = "Password is required"
            isValid = false
        }

        if (selectedUserType == null) {
            Toast.makeText(this, "Please select a user type", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        if(!checkBox.isChecked){
            Toast.makeText(this, "Please Accept Terms and Conditions", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }

    private fun showLoader(show: Boolean) {
        if (show) {
            btnContinue.isEnabled = false
            btnContinue.text = "Loading..."
        } else {
            btnContinue.isEnabled = true
            btnContinue.text = "Login"
        }
    }

    private fun navigateToNextScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}
