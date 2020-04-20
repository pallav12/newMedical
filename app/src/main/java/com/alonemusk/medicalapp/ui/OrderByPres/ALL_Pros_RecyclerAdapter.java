package com.alonemusk.medicalapp.ui.OrderByPres;

/**
 * Created by Shade on 5/9/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.alonemusk.medicalapp.R;

import java.util.ArrayList;

public class ALL_Pros_RecyclerAdapter extends RecyclerView.Adapter<ALL_Pros_RecyclerAdapter.ViewHolder>
//        RecyclerView.Adapter<RecyclerAdapter_Seeall.ViewHolder>
//


{
    ArrayList<OrderModel> orderModels = new ArrayList<>();


    Context mcontext;
    AllorderDetailsInterface allorderDetailsInterface;

    public ALL_Pros_RecyclerAdapter(Context context, ArrayList<OrderModel> orderModels, AllorderDetailsInterface allorderDetailsInterface) {
        mcontext = context;
        this.orderModels = orderModels;
        this.allorderDetailsInterface = allorderDetailsInterface;


    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private EditText status;
        private EditText orderId;
        private EditText name;
        private EditText address;
        private EditText deliveryDate;
        private EditText amount;
        private EditText paymentStatus;
        AllorderDetailsInterface allorderDetailsInterface;

        public ViewHolder(View itemView, final AllorderDetailsInterface allorderDetailsInterface) {
            super(itemView);
            this.allorderDetailsInterface = allorderDetailsInterface;
            orderId = (EditText) itemView.findViewById(R.id.orderID);
            status = itemView.findViewById(R.id.status);
            address = itemView.findViewById(R.id.address);
            deliveryDate = itemView.findViewById(R.id.delivery_date);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            paymentStatus = itemView.findViewById(R.id.paymentStatus);


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
        ViewHolder viewHolder = new ViewHolder(v, allorderDetailsInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.orderId.setText("" + orderModels.get(i).getOrder_id());
        viewHolder.status.setText(orderModels.get(i).getStatus());
        viewHolder.address.setText("" + orderModels.get(i).getAddress_id());
    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }
}