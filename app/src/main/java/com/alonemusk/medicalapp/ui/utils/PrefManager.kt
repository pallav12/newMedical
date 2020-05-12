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
}