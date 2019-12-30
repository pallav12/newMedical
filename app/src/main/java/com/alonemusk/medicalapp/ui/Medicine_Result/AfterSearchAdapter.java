package com.alonemusk.medicalapp.ui.Medicine_Result;

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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AfterSearchAdapter extends RecyclerView.Adapter<AfterSearchAdapter.holder> {
    ArrayList<String>str;
    Context context;
    GoTOCart goTOCart;
    public AfterSearchAdapter(ArrayList<String>str, Context context,GoTOCart goTOCart){
        this.str=str;
        Log.d("strrr",str.size()+"");
        this.goTOCart=goTOCart;

        this.context=context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_medicine,null);
        holder holder=new holder(v,goTOCart);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {


       // holder.textView.setText(str.get(position));

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView textView;

        public holder(@NonNull View itemView, final GoTOCart goTOCart) {
            super(itemView);
            textView=itemView.findViewById(R.id.title);
            itemView.findViewById(R.id.addtocartbtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { addtocart_volley(getAdapterPosition());







                }
            });


        }
    }
    public interface GoTOCart{
        public void gotocart(int position);
    }
    public void addtocart_volley(final int getadapterposition){




            Toast.makeText(context, "in vooly", Toast.LENGTH_SHORT).show();
            RequestQueue queue = Volley.newRequestQueue(context);

            // JSONObject urlf = new JSONObject(data);
            JSONObject data2 = new JSONObject();

            try {
                data2.put("user_id", 10001);
                data2.put("medicine_id", 10001);
                data2.put("quantity", 5);



            } catch (Exception e) {

            }


            final JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST
                    , "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/cart/insert-medicine-cart", data2,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(context, "successfully inserted into cart", Toast.LENGTH_SHORT).show();
                            goTOCart.gotocart(getadapterposition);
                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "volly error   " + error, Toast.LENGTH_SHORT).show();

                        }
                    }
            ) {

                @Override
                public HashMap<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    //  params.put("Content-Type", " text/javascript");
                    params.put("Content-Type", "application/json");

                    return params;


                }


            };


            queue.add(putRequest);







    }
}
