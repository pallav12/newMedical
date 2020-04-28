package com.alonemusk.medicalapp.ui.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alonemusk.medicalapp.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.holder> {

    Context context;
    Go_TO_Cart goTOCart;
    ArrayList<CartDetails> cartDetails;

    public CartAdapter(Context context, ArrayList<CartDetails> cartDetails, Go_TO_Cart goTOCart) {
        this.goTOCart = goTOCart;


        this.context = context;
        this.cartDetails = cartDetails;

    }


    public void setCartDetails(ArrayList<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cart, null);
        holder holder = new holder(v, goTOCart);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.Cart_Medicine_Name_Id.setText("" + cartDetails.get(position).getMedicine_name());
        holder.Quantity_medicine_in_cart.setText("" + cartDetails.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartDetails.size();
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView Cart_Medicine_Name_Id = itemView.findViewById(R.id.cart_medicine_name_id);
        // Spinner spinner=itemView.findViewById(R.id.quantity_medicine_in_cart);
        Go_TO_Cart goTOCart;
        TextView Quantity_medicine_in_cart = itemView.findViewById(R.id.quantity_medicine_in_cart);
        ImageView delete = itemView.findViewById(R.id

                .delete_cart_element);
        Button increase = itemView.findViewById(R.id.quantity_increase);
        Button decrease = itemView.findViewById(R.id.quantitydecrease);

        public holder(@NonNull View itemView, final Go_TO_Cart goTOCart) {
            super(itemView);
            this.goTOCart = goTOCart;


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if (goTOCart != null && position != RecyclerView.NO_POSITION) {
                        goTOCart.OnDeleteClicked(position);
                    }
                }
            });
            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if (goTOCart != null && position != RecyclerView.NO_POSITION) {
                        goTOCart.OnIncreaseClicked(position);
                    }
                }
            });
            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if (goTOCart != null && position != RecyclerView.NO_POSITION) {
                        goTOCart.OnDecreaseClicked(position);
                    }
                }
            });


        }


    }


}


