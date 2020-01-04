package com.alonemusk.medicalapp.ui.Checkout;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Confirm_order_fregment extends Fragment {
    public static int selected_address=0;
RecyclerView Confirm_order_recyclerview;
LinearLayout confirmlinearlayout;
    private ConfirmOrderFregmentViewModel mViewModel;
    ArrayList<CartDetails> cartDetailsArrayList;
    Button GotocartBack;
    Button gotopayment;
    int total_price=0;
    Button Changeaddressbtn;
    TextView Total_price;

  //  ProcessBuilder processBuilder;
    public static Confirm_order_fregment newInstance() {
        return new Confirm_order_fregment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.confirm_order_fregment_fragment, container, false);
       Confirm_order_recyclerview=v.findViewById(R.id.confirm_order_recyclerview);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Confirm_order_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
       cartDetailsArrayList=new ArrayList<>();
       Total_price=v.findViewById(R.id.total_price);
        Changeaddressbtn=v.findViewById(R.id.changeaddressbtn);
        Toast.makeText(getActivity(), ""+selected_address, Toast.LENGTH_SHORT).show();
       GotocartBack=v.findViewById(R.id.gottocart);
       //quantity_medicine_in_cart=v.findViewById(R.id.quantity_medicine_in_cart);
       gotopayment=v.findViewById(R.id.gotopayment);
       confirmlinearlayout=v.findViewById(R.id.linearlayoutconfirmfrag);
Changeaddressbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        navController.navigate(R.id.action_confirm_order_fregment_to_navigation_adress);

    }
});
       GotocartBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getActivity().onBackPressed();
           }
       });
       gotopayment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               navController.navigate(R.id.action_confirm_order_fregment_to_paymentMethodFragment);
           }
       });
       return  v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConfirmOrderFregmentViewModel.class);
        // TODO: Use the ViewModel
volly();

    }

    private void volly(){
        String url="http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/cart/get-user-cart/"+10001;


        final RequestQueue queue = Volley.newRequestQueue(getActivity());

        //  Toast.makeText(Mutual_details.this,""+url,Toast.LENGTH_SHORT).show();








        final StringRequest jsonRequest = new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "nothing "+response, Toast.LENGTH_SHORT).show();
                //    Toast.makeText(getActivity(), "cart fetched this", Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();

                ArrayList<JSONObject> jsonObjects= JsonParsing.parsejson(response);
                cartDetailsArrayList.clear();
                for(JSONObject obj:jsonObjects){
                    CartDetails object = gson.fromJson(String.valueOf(obj), CartDetails.class);
                    cartDetailsArrayList.add(object);
                    total_price=total_price+object.getQuantity()*object.getDiscount_price();
                }
                Total_price.setText(""+total_price);

                Confirm_order_recyclerview.setAdapter(new ConfirmorderAdapter(getContext(),cartDetailsArrayList));
confirmlinearlayout.setVisibility(View.VISIBLE);
            }


        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(Mutual_details.this,"check internet connection"+error,Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "cart not fetched"+error, Toast.LENGTH_SHORT).show();

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
