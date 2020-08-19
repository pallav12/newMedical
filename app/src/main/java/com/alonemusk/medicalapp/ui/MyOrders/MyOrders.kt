package com.alonemusk.medicalapp.ui.MyOrders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.Search.SearchMedicine
import com.alonemusk.medicalapp.ui.models.Medicine
import com.alonemusk.medicalapp.ui.utils.PrefManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyOrders : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)
        val recyclerView:RecyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val array=ArrayList<String>()
        val adapter=OrderAdapter(array)
        recyclerView.adapter=adapter
        FirebaseDatabase.getInstance().getReference("order").child(PrefManager.getString("uid","not_defined") as String)
                .addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val arrayListener=ArrayList<String>()
                        for(medicines in p0.children){
                            arrayListener.add(medicines.key as String)
                        }
                        adapter.update(arrayListener)

                    }

                })
    }
}
