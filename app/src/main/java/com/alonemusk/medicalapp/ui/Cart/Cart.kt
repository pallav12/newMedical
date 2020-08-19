package com.alonemusk.medicalapp.ui.Cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.Search.SearchAdapter
import com.alonemusk.medicalapp.ui.Search.SearchMedicine
import com.alonemusk.medicalapp.ui.new_home.MySearchableFragmentRecyclerViewAdapter
import com.alonemusk.medicalapp.ui.utils.PrefManager
import com.alonemusk.medicalapp.ui.utils.Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Cart : AppCompatActivity(), MySearchableFragmentRecyclerViewAdapter.onClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val arrayList=ArrayList<SearchMedicine>()
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        val adapter=MySearchableFragmentRecyclerViewAdapter(arrayList, this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter

        FirebaseDatabase.getInstance().getReference("cart").child(PrefManager.getId()).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                for(medicine in p0.children){
                    val medicin:SearchMedicine=medicine.getValue(SearchMedicine::class.java) as SearchMedicine
                    arrayList.add(medicin)
                }
                adapter.update(arrayList)

            }


        })

    }

    override fun onClick(medicine: SearchMedicine) {
        Utils.toast("Not yet implemented")
    }
}
