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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ALL_Pros_RecyclerAdapter extends RecyclerView.Adapter<ALL_Pros_RecyclerAdapter.ViewHolder>
//        RecyclerView.Adapter<RecyclerAdapter_Seeall.ViewHolder>
//


{
    ArrayList<OrderModel> orderModels=new ArrayList<>();




    Context mcontext;
    AllorderDetailsInterface allorderDetailsInterface;

    public ALL_Pros_RecyclerAdapter(Context context,ArrayList<OrderModel> orderModels,AllorderDetailsInterface allorderDetailsInterface) {
      mcontext=context;
    this.orderModels=orderModels;
    this.allorderDetailsInterface=allorderDetailsInterface;


    }









    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;

        public TextView itemTitle;
        public TextView orderstatus;
        public TextView orderaddress;
AllorderDetailsInterface allorderDetailsInterface;
        public ViewHolder(View itemView, final AllorderDetailsInterface allorderDetailsInterface) {
            super(itemView);
this.allorderDetailsInterface=allorderDetailsInterface;
            itemTitle = (TextView)itemView.findViewById(R.id.all_pres_list_item_textview);
            orderstatus=itemView.findViewById(R.id.orderStatus);
            orderaddress=itemView.findViewById(R.id.orderAddress);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
allorderDetailsInterface.GOTOOrderDetails(getAdapterPosition());


                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.all_pres_list_layout_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v,allorderDetailsInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(""+orderModels.get(i).getOrder_id());
        viewHolder.orderstatus.setText(orderModels.get(i).getStatus());
        viewHolder.orderaddress.setText(""+orderModels.get(i).getAddress_id());



    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }
}