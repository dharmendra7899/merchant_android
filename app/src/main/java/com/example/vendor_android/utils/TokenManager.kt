package com.example.vendor_android.utils

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    // Save token
    fun saveToken(token: String) {
        prefs.edit().putString("auth_token", token).apply()
    }

    // Get token
    fun getToken(): String? {
        return prefs.getString("auth_token", null)
    }

    // Clear token (e.g., on logout)
    fun clearToken() {
        prefs.edit().remove("auth_token").apply()
    }
}
