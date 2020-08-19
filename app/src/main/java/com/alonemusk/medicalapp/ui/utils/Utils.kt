package com.alonemusk.medicalapp.ui.utils

import android.widget.Toast
import com.alonemusk.medicalapp.MainApplication
import java.util.*

class Utils {
    companion object {
        fun toast(str: String) {
            Toast.makeText(MainApplication.getInstance().applicationContext, str, Toast.LENGTH_SHORT).show()
        }
        fun getTime():String{
            val date= Calendar.getInstance().time
            return date.toString()
        }
    }
}