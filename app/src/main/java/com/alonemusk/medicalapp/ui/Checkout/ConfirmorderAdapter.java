package com.alonemusk.medicalapp.ui.Checkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.Cart.CartDetails;

import java.util.ArrayList;

public class ConfirmorderAdapter extends RecyclerView.Adapter<ConfirmorderAdapter.holder> {
    Context context;
    GoTOCart goTOCart;
    ArrayList<CartDetails> cartDetails;

    public ConfirmorderAdapter(Context context, ArrayList<CartDetails> cartDetails) {

        this.goTOCart = goTOCart;

        this.context = context;
        this.cartDetails = cartDetails;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemconfirmorder, null);
        holder holder = new holder(v, goTOCart);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.Cart_Medicine_Name_Id.setText("" + cartDetails.get(position).getMedicine_name());
        holder.Confirm_order_quantity_id.setText("qt -: " + cartDetails.get(position).getQuantity());
       // holder.price.setText("" + cartDetails.get(position).getDiscount_price() * cartDetails.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartDetails.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView price = itemView.findViewById(R.id.confirorder_pricetextview);
        TextView Confirm_order_quantity_id = itemView.findViewById(R.id.confirm_order_quantity_id);
        TextView Cart_Medicine_Name_Id = itemView.findViewById(R.id.cart_medicine_name_id);
        Spinner spinner = itemView.findViewById(R.id.quantity_medicine_in_cart);
        ImageView delete = itemView.findViewById(R.id.delete_cart_element);

        public holder(@NonNull View itemView, final GoTOCart goTOCart) {
            super(itemView);

            final int position = getAdapterPosition();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteProductFromCart(position);
//                    switch (v.getId()){
//                        case R.id.delete_cart_element :
//                        {

                    // }


                    // }


                }


            });
//delete.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
//      //
//    }
//});
        }

        public void DeleteProductFromCart(int position) {


        }

    }

    public interface GoTOCart {
        public void gotocart(int position);

    }

}


