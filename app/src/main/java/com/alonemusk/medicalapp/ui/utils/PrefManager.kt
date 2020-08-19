package com.alonemusk.medicalapp.ui.utils

import android.content.Context
import android.preference.PreferenceManager
import com.alonemusk.medicalapp.MainApplication
import com.alonemusk.medicalapp.ui.utils.Constants.USER_ID

object PrefManager {
    private val preference by lazy { PreferenceManager.getDefaultSharedPreferences(context) }
    private val context: Context
        get() = MainApplication.getInstance().applicationContext

    fun putBoolean(preferenceKey: String, preferenceValue: Boolean) {
        preference.edit().putBoolean(preferenceKey, preferenceValue).apply()
    }

    fun getBoolean(preferenceKey: String, defaultValue: Boolean): Boolean {
        return preference.getBoolean(preferenceKey, defaultValue)
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

    fun getId(): String{
        return PrefManager.getString(USER_ID,"not_defined") as String
    }
}