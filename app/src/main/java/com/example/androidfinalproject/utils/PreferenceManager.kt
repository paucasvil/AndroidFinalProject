package com.example.androidfinalproject.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun isFirstTimeLaunch(): Boolean {
        return prefs.getBoolean("isFirstTimeLaunch", true)
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean("isFirstTimeLaunch", isFirstTime)
        editor.apply()
    }
}