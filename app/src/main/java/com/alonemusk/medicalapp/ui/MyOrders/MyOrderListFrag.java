package com.alonemusk.medicalapp.ui.MyOrders;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alonemusk.medicalapp.BaseAddress;
import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.classes.JsonParsing;
import com.alonemusk.medicalapp.ui.Cart.CartAdapter;
import com.alonemusk.medicalapp.ui.Cart.CartDetails;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyOrderListFrag extends Fragment implements OrderListInterface{
    OrderListInterface orderListInterface;
    private MyOrderListViewModel mViewModel;
ArrayList<Order> orders =new ArrayList<>();
RecyclerView AllorderRecyclerView;

    public static MyOrderListFrag newInstance() {
        return new MyOrderListFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.my_order_list_fragment, container, false);
        AllorderRecyclerView=v.findViewById(R.id.allorderRecyclerView);
orderListInterface=this;
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyOrderListViewModel.class);
        // TODO: Use the ViewModel
          int user_id=10001;
          getallorder(user_id);
    }
public void getallorder(int user_id){

    String url=(new BaseAddress()).getBaseurl()+"/cart/all-order-details-user/"+user_id;


    final RequestQueue queue = Volley.newRequestQueue(getActivity());

    //  Toast.makeText(Mutual_details.this,""+url,Toast.LENGTH_SHORT).show();








    final StringRequest jsonRequest = new StringRequest(Request.Method.GET, url
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            orders.clear();
            Gson gson = new Gson();
             Toast.makeText(getActivity(), "some "+response, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObject=new JSONObject(response);
                JSONArray jsonObject1=jsonObject.getJSONArray("order_ids");
                JSONArray jsonObject2=jsonObject.getJSONArray("details");
               for(int i=0;i<jsonObject1.length();i++){
                   JSONObject obj=jsonObject1.getJSONObject(i);
                   Order object = gson.fromJson(String.valueOf(obj), Order.class);
                orders.add(object);

               }
               AllorderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
               AllorderRecyclerView.setAdapter(new AllOrderAdapter(getContext(), orders,orderListInterface));

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

    @Override
    public void GoToOrderDetails(int position) {
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle b=new Bundle();
        b.putInt("order_id",orders.get(position).getOrder_id());
        navController.navigate(R.id.action_myOrderListFrag_to_myOrderDetailsFrag,b);
    }
}
