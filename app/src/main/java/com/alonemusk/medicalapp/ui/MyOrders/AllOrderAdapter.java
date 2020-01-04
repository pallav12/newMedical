package com.alonemusk.medicalapp.ui.MyOrders;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.holder> {
    ArrayList<Order> orders=new ArrayList<>();
    Context context;
    OrderListInterface orderListInterface;

    public AllOrderAdapter(Context context,ArrayList<Order> orders,OrderListInterface orderListInterface){
        this.orders=orders;
this.orderListInterface=orderListInterface;


        this.context=context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.allorderadapteritem,null);
        holder holder=new holder(v,orderListInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
holder.orderIdtextview.setText(""+(orders.get(position).getOrder_id()));
holder.orderstatustextview.setText(""+orders.get(position).getStatus());


       // holder.textView.setText(str.get(position));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView orderIdtextview,orderstatustextview;
OrderListInterface orderListInterface;
        public holder(@NonNull View itemView, final OrderListInterface orderListInterface) {
            super(itemView);
            orderIdtextview=itemView.findViewById(R.id.Order_IdTextView);
            orderstatustextview=itemView.findViewById(R.id.Order_statusTextView);
            this.orderListInterface=orderListInterface;
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        orderListInterface.GoToOrderDetails(getAdapterPosition());
    }
});


        }
    }
    public interface GoTOCart{
        public void gotocart(int position);
    }
}
