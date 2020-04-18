package com.alonemusk.medicalapp.ui.OrderByPres;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
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

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.classes.JsonParsing;
import com.alonemusk.medicalapp.ui.Cart.CartAdapter;
import com.alonemusk.medicalapp.ui.Cart.CartDetails;
import com.alonemusk.medicalapp.ui.Cart.Go_TO_Cart;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AllPresListFrag extends Fragment implements View.OnContextClickListener ,AllorderDetailsInterface{

    private AllPresListViewModel mViewModel;
RecyclerView allpresrecyclerview;
    RecyclerView.LayoutManager layoutManager_noti;
FloatingActionButton floatingActionButton;
    NavController navController;
    AllorderDetailsInterface allorderDetailsInterface;
    public static AllPresListFrag newInstance() {
        return new AllPresListFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.all_pres_list_fragment, container, false);
        allpresrecyclerview=root.findViewById(R.id.all_pres_list_Recyclerview);
       floatingActionButton=root.findViewById(R.id.floatingactionbuttonid);
        layoutManager_noti = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
           allpresrecyclerview.setLayoutManager(layoutManager_noti);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "floating clicked ", Toast.LENGTH_SHORT).show();

        navController.navigate(R.id.action_allPresListFrag_to_submitPresDetailsFrag);
    }
});
            allorderDetailsInterface=this;
            getAllorder();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AllPresListViewModel.class);
        // TODO: Use the ViewModel




      //  ALL_Pros_RecyclerAdapter adapter1;
//        adapter1 = new ALL_Pros_RecyclerAdapter(getActivity());
//        allpresrecyclerview.setAdapter(adapter1);
    }

    @Override
    public boolean onContextClick(View view) {
        switch (view.getId()){
            case R.id.floatingactionbuttonid:
            {

                return true;
            }
        }





        return false;
    }
    public void getAllorder(){

            String url="http://ec2-13-235-73-199.ap-south-1.compute.amazonaws.com:3000/get-user-order-detail/"+10001;


            final RequestQueue queue = Volley.newRequestQueue(getActivity());

            //  Toast.makeText(Mutual_details.this,""+url,Toast.LENGTH_SHORT).show();








            final StringRequest jsonRequest = new StringRequest(Request.Method.GET, url
                    , new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getActivity(), "nothing "+response, Toast.LENGTH_LONG).show();
                    //    Toast.makeText(getActivity(), "cart fetched this", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();

                    ArrayList<JSONObject> jsonObjects= JsonParsing.parsejson(response);


                   ArrayList<OrderModel> orderModel=new ArrayList<>();
                    for(JSONObject obj:jsonObjects){
                        OrderModel object = gson.fromJson(String.valueOf(obj), OrderModel.class);
                        orderModel.add(object);
                    }
                    AllData allData=new AllData();
                    allData.orderModels=orderModel;
                    if(orderModel.size()>0) {
                        allpresrecyclerview.setAdapter(new ALL_Pros_RecyclerAdapter(getActivity(), orderModel,allorderDetailsInterface));
                    }
else {
                        Toast.makeText(getActivity(), "nothing is there in cart", Toast.LENGTH_LONG).show();

                    }
                }


            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {



                    Toast.makeText(getActivity(), "no order is there please add your order", Toast.LENGTH_SHORT).show();

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
    public void GOTOOrderDetails(int position) {
        Bundle b=new Bundle();
        b.putInt("position",position);
        navController.navigate(R.id.action_allPresListFrag_to_presOrderDetails,b);
    }
}

