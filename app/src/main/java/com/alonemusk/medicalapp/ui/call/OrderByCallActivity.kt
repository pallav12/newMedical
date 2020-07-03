package com.alonemusk.medicalapp.ui.call

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alonemusk.medicalapp.R

class OrderByCallActivity : AppCompatActivity() {
    private lateinit var progress:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_by_call)
        progress=findViewById(R.id.progress)
        supportFragmentManager.beginTransaction().add(R.id.container, OrderByCallFragment()).commit()
        val viewModel = ViewModelProviders.of(this).get(CallNowViewModel::class.java)
        viewModel.fragmentTransaction.observe(this, Observer {
            supportFragmentManager.beginTransaction().addToBackStack("yo").add(R.id.container, it).commit()
        })
        viewModel.progress.observe(this, Observer {
            if(it){
                progress.visibility= View.VISIBLE
            }
            else{
                progress.visibility= View.GONE
            }
        })
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
