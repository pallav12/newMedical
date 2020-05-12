package com.alonemusk.medicalapp.ui.utils

import android.widget.Toast
import com.alonemusk.medicalapp.MainApplication

class General {
    companion object {
        fun toast(str: String) {
            Toast.makeText(MainApplication.getInstance().applicationContext, str, Toast.LENGTH_SHORT).show()
        }
    }
}