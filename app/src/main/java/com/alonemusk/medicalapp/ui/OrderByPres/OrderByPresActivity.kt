package com.alonemusk.medicalapp.ui.OrderByPres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alonemusk.medicalapp.R
import kotlinx.android.synthetic.main.activity_flash.*

class OrderByPresActivity : AppCompatActivity() {
    lateinit var viewmodel: OrderByPresViewmodel
    lateinit var progress:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_by_pres)
        viewmodel=ViewModelProviders.of(this).get(OrderByPresViewmodel::class.java)
        viewmodel.fragmentTransaction.observe(this, Observer {
            supportFragmentManager.beginTransaction().replace(R.id.container,it).commit()
        })
        progress=findViewById(R.id.progress)
        viewmodel.progress.observe(this, Observer {
            if(it){
                progress.visibility= View.VISIBLE
            }
            else{
                progress.visibility=View.GONE
            }
        })
    }
}
