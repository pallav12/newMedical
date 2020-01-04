package com.alonemusk.medicalapp.ui.Account;

/**
 * Created by Shade on 5/9/2016.
 */



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alonemusk.medicalapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Address_RecyclerAdapter extends RecyclerView.Adapter<Address_RecyclerAdapter.ViewHolder>

{
    Context mcontext;

    public int mtype;
   public ArrayList<String> arrayList1=new ArrayList<String>();
    public Address_RecyclerAdapter(Context context) {

      mcontext=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(mcontext).inflate(R.layout.address_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }


    class ViewHolder extends  RecyclerView.ViewHolder{


        public TextView itemTitle;

public ViewHolder(View itemView){
        super(itemView);



        }
    }



}
