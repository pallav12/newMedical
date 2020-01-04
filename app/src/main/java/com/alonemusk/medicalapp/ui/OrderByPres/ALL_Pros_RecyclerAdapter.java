package com.alonemusk.medicalapp.ui.OrderByPres;

/**
 * Created by Shade on 5/9/2016.
 */

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ALL_Pros_RecyclerAdapter extends RecyclerView.Adapter<ALL_Pros_RecyclerAdapter.ViewHolder>
//        RecyclerView.Adapter<RecyclerAdapter_Seeall.ViewHolder>
//


{
    String samplearray[]={
      "first",
      "second",
      "third"
    };



    Context mcontext;

    public ALL_Pros_RecyclerAdapter(Context context) {
      mcontext=context;



    }









    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;

        public TextView itemTitle;
        public TextView itemDetail;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = (TextView)itemView.findViewById(R.id.all_pres_list_item_textview);

            linearLayout=itemView.findViewById(R.id.all_pres_list_item_layoutid);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  switch (v.getId()){
                      case R.id.all_pres_list_item_layoutid :
                      {
                          Toast.makeText(mcontext, "clicked", Toast.LENGTH_SHORT).show();
                      }



                  }


                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.all_pres_list_layout_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(samplearray[i]);


    }

    @Override
    public int getItemCount() {
        return samplearray.length;
    }
}