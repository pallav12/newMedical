package com.alonemusk.medicalapp.ui.utils

import android.content.Context
import android.preference.PreferenceManager
import com.alonemusk.medicalapp.MainApplication

object PrefManager {
    private val preference by lazy { PreferenceManager.getDefaultSharedPreferences(context) }
    private val context: Context
        get() = MainApplication.getInstance().applicationContext

    fun putBoolean(preferenceKey: Int, preferenceValue: Boolean) {
        preference.edit().putBoolean(context.getString(preferenceKey), preferenceValue).apply()
    }

    fun getBoolean(preferenceKey: Int, defaultValue: Boolean): Boolean {
        return preference.getBoolean(context.getString(preferenceKey), defaultValue)
    }

    fun putString(preferenceKey: String, preferenceValue: String) {
        preference.edit().putString(preferenceKey, preferenceValue).apply()
    }

    fun getString(preferenceKey: String, defaultValue: String): String? {
        return preference.getString(preferenceKey, defaultValue)
    }

    fun getPhone(): String {
        return getString(Constants.PHONE, "not_defined") as String
    }
}