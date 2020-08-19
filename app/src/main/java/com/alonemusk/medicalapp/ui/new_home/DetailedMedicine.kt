package com.alonemusk.medicalapp.ui.new_home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.OrderByPres.OrderByPresActivity
import com.alonemusk.medicalapp.ui.Search.SearchMedicine
import com.alonemusk.medicalapp.ui.utils.Constants
import com.alonemusk.medicalapp.ui.utils.PrefManager
import com.alonemusk.medicalapp.ui.utils.Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailedMedicine : AppCompatActivity() {
    lateinit var medicine: SearchMedicine
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_medicine)
        val id = intent.getStringExtra("medicine_id")
        val textView: TextView = findViewById(R.id.medicine_name)
        val addCart:Button=findViewById(R.id.add_to_cart)
        val cost: TextView = findViewById(R.id.cost)
        val discount: TextView = findViewById(R.id.discount)
        val submit: Button = findViewById(R.id.submit)
        FirebaseDatabase.getInstance().getReference("medicines").child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                medicine = p0.getValue(SearchMedicine::class.java) as SearchMedicine
                cost.text = "Cost : " + medicine?.cost.toString()
                discount.text="Discount: " +medicine.discount
                submit.visibility = View.VISIBLE
                addCart.visibility=View.VISIBLE
                textView.text=medicine.name
                if (!medicine.pres_needed) {
                    submit.text = "Order"
                    submit.setOnClickListener {
                        submit.visibility=View.GONE
                        FirebaseDatabase.getInstance().getReference("order")
                                .child(PrefManager.getString("uid", "not_defined") as String)
                                .push().setValue(1)
                                .addOnSuccessListener {
                                    Utils.toast("Order placed")
                                }
                                finish()
                    }

                } else {
                    if (PrefManager.getBoolean(Constants.PREF_SUBMITTED, false)) {
                        submit.text = "Review medicine"
                        Utils.toast("In progress")
                    } else {
                        submit.text = "Submit prescription"
                        submit.setOnClickListener {
                            startActivity(Intent(applicationContext, OrderByPresActivity::class.java))
                            finish()
                        }
                    }
                }
            }

        })
        addCart.setOnClickListener {
            FirebaseDatabase.getInstance().getReference("cart").child(PrefManager.getId()).push().setValue(medicine).addOnSuccessListener {
                Utils.toast("Successfully added in cart")
                finish()
            }
        }
    }
}
