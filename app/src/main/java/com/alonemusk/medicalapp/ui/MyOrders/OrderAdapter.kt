package com.alonemusk.medicalapp.ui.MyOrders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.Search.SearchMedicine

class OrderAdapter( var arrayList:ArrayList<String>) :RecyclerView.Adapter<OrderAdapter.holder>() {
    public class holder(val view:View) : RecyclerView.ViewHolder(view){
        val textView:TextView=view.findViewById(R.id.tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.my_orders,null)
        return holder(view)
    }
    fun update(arrayList: ArrayList<String>){
        this.arrayList=arrayList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.textView.text=arrayList[position]
    }

}