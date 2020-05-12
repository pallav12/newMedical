package com.alonemusk.medicalapp.ui.Login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.utils.PrefManager

class FlashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)
        PrefManager.putBoolean(R.string.first_time,false)
        findViewById<Button>(R.id.login).setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}
