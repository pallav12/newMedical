package com.alonemusk.medicalapp.ui.new_home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.Search.SearchMedicine
import kotlinx.android.synthetic.main.fragment_searchable_fragment.view.*


class MySearchableFragmentRecyclerViewAdapter(
        private var mValues: List<SearchMedicine>, private var click:onClick)
    : RecyclerView.Adapter<MySearchableFragmentRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_searchable_fragment, parent, false)
        return ViewHolder(mValues,view,click)
    }
    public fun update(array:List<SearchMedicine>){
        this.mValues=array
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name.toString()

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(array: List<SearchMedicine>,val mView: View,val click:onClick) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        init {
            mView.setOnClickListener{
                click.onClick(array[adapterPosition])
            }
        }

    }
    interface onClick{
        fun onClick(medicine:SearchMedicine);
    }
}
