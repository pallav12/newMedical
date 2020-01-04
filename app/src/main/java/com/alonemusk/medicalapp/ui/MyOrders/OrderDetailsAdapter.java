package com.alonemusk.medicalapp.ui.MyOrders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alonemusk.medicalapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.holder> {
    ArrayList<OrderDetails> orderDetails=new ArrayList<>();
    Context context;


    public OrderDetailsAdapter(Context context, ArrayList<OrderDetails> orderDetails){
        this.orderDetails=orderDetails;



        this.context=context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.order_details_item,null);
        holder holder=new holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
holder.Order_name_medicine_textview.setText(""+orderDetails.get(position).getMedicine_id());
holder.Order_medicine_quantity_textview.setText(""+orderDetails.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView Order_name_medicine_textview,Order_medicine_quantity_textview;

        public holder(@NonNull View itemView) {
            super(itemView);
            Order_name_medicine_textview=itemView.findViewById(R.id.order_name_medicine_textview);
    Order_medicine_quantity_textview=itemView.findViewById(R.id.order_medicine_quantity_textview);





        }
    }
    public interface GoTOCart{
        public void gotocart(int position);
    }
}
