package com.alonemusk.medicalapp.ui.Checkout;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alonemusk.medicalapp.BaseAddress;
import com.alonemusk.medicalapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class PaymentMethodFragment extends Fragment {
CardView cod;
    NavController navController;
    private PaymentMethodViewModel mViewModel;

    public static PaymentMethodFragment newInstance() {
        return new PaymentMethodFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.payment_method_fragment, container, false);
       cod=v.findViewById(R.id.cod);
      navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
       cod.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                placeorder();

           }
       });
       return v;
    }
    private void placeorder(){
String url=(new BaseAddress()).getBaseurl() +"/cart/submit-order";



        Toast.makeText(getActivity(), "in addtocart", Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // JSONObject urlf = new JSONObject(data);
        JSONObject data2 = new JSONObject();

        try{
            data2.put("user_id",getArguments().getInt("user_id"));
          data2.put("address_id",getArguments().getInt("address_id"));




        }catch(Exception e){

        }


        final JsonObjectRequest putRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST
                , url, data2,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();


                        navController.navigate(R.id.action_paymentMethodFragment_to_orderPlacedFrag);

                       // reload();
                    }


                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "volly error   " + error, Toast.LENGTH_SHORT).show();

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void reload() {
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PaymentMethodViewModel.class);
        // TODO: Use the ViewModel
    }

}
