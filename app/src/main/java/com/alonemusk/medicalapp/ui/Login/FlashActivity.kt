package com.alonemusk.medicalapp.ui.Login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.utils.Constants
import com.alonemusk.medicalapp.ui.utils.PrefManager
import android.util.Pair as UtilPair


class FlashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)
        window.exitTransition = null;
        PrefManager.putBoolean(Constants.FIRST_TIME, false)
        val imageBackground = findViewById<View>(R.id.view)
        val image = findViewById<ImageView>(R.id.imageView2)
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,
                    UtilPair.create(login,"login"),
                    UtilPair.create(imageBackground,"image_background"),
                    UtilPair.create(image,"image"))
            startActivity(intent, options.toBundle())

            val handler = Handler()
            handler.postDelayed(this::finish, 1000)
        }

    }
}
