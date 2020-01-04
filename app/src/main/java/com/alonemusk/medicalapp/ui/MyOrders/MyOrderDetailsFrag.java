package com.alonemusk.medicalapp.ui.MyOrders;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alonemusk.medicalapp.BaseAddress;
import com.alonemusk.medicalapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyOrderDetailsFrag extends Fragment {
ArrayList<OrderDetails> orderDetails=new ArrayList<>();
    private MyOrderDetailsViewModel mViewModel;
    private RecyclerView OrderDetailsRecyclerView;

    public static MyOrderDetailsFrag newInstance() {
        return new MyOrderDetailsFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
 View v= inflater.inflate(R.layout.my_order_details_fragment, container, false);
 OrderDetailsRecyclerView=v.findViewById(R.id.orderDetailsRecyclerView);
return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyOrderDetailsViewModel.class);
        // TODO: Use the ViewModel
        int order_id=getArguments().getInt("order_id");

        getDetailsOrder(order_id);
    }
   public void getDetailsOrder(int order_id){


       String url=(new BaseAddress()).getBaseurl()+"/cart/order-details/"+order_id;


       final RequestQueue queue = Volley.newRequestQueue(getActivity());

       //  Toast.makeText(Mutual_details.this,""+url,Toast.LENGTH_SHORT).show();








       final StringRequest jsonRequest = new StringRequest(Request.Method.GET, url
               , new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               orderDetails.clear();
               Gson gson = new Gson();
               Toast.makeText(getActivity(), "some "+response, Toast.LENGTH_SHORT).show();
               try {
                   JSONArray jsonArray=new JSONArray(response);

                   for(int i=0;i<jsonArray.length();i++){
                       JSONObject obj=jsonArray.getJSONObject(i);
                       OrderDetails object = gson.fromJson(String.valueOf(obj), OrderDetails.class);
                       orderDetails.add(object);

                   }
                   OrderDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                   OrderDetailsRecyclerView.setAdapter(new OrderDetailsAdapter(getContext(), orderDetails));

               } catch (JSONException e) {
                   e.printStackTrace();
               }
               //    Toast.makeText(getActivity(), "cart fetched this", Toast.LENGTH_SHORT).show();
//            Gson gson = new Gson();
//            orders.clear();
//            ArrayList<JSONObject> jsonObjects= JsonParsing.parsejson(response);
//
//            for(JSONObject obj:jsonObjects){
//                CartDetails object = gson.fromJson(String.valueOf(obj), CartDetails.class);
//                cartDetails.add(object);
//            }
//            if(cartDetails.size()>0) {
//                Cart_Recycler_View.setAdapter(new CartAdapter(getActivity(), cartDetails, go_to_cart));
//            }
//
//            Toast.makeText(getActivity(), "nothing is there in cart", Toast.LENGTH_SHORT).show();
//

           }


       }, new Response.ErrorListener() {
           public void onErrorResponse(VolleyError error) {
               //  Toast.makeText(Mutual_details.this,"check internet connection"+error,Toast.LENGTH_SHORT).show();
               Toast.makeText(getActivity(), "cart not fetched   "+error.getMessage(), Toast.LENGTH_SHORT).show();

//            cartDetails.clear();
//
//            Cart_Recycler_View.setAdapter(new CartAdapter(getActivity(), cartDetails, go_to_cart));


               Toast.makeText(getActivity(), "nothing is there in cart", Toast.LENGTH_SHORT).show();

           }

       }){
           @Override
           public HashMap<String, String> getHeaders() throws AuthFailureError {
               HashMap<String, String> params = new HashMap<>();
               //  params.put("Content-Type", " text/javascript");
               params.put("Content-Type", "application/json");

               return params;



           }
       }

               ;


// Add the request to the RequestQueue.
       queue.add(jsonRequest);









   }

}
