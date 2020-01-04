package com.alonemusk.medicalapp.ui.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.article.ArticleViewModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Address extends Fragment {
    RecyclerView recyclerView1;


    RecyclerView.LayoutManager layoutManager1;

    RecyclerView.Adapter adapter1;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.addressfragment,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getlist();

        recyclerView1 = (RecyclerView)getActivity().findViewById(R.id.address_recycler_view);
        layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter1 = new Address_RecyclerAdapter(getActivity());
        recyclerView1.setAdapter(adapter1);

    }
ArrayList<String> address_name=new ArrayList<>();
void getlist(){

    String url="http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/user/fetch-alladdress";


    final RequestQueue queue = Volley.newRequestQueue(getActivity());










    final StringRequest jsonRequest = new StringRequest(Request.Method.GET, url
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArray=new JSONArray(response);
                int n=jsonArray.length();
                    for(int i=0;i<n;i++){
                        address_name.add(jsonArray.getJSONObject(i).getString("address"));
                    }


                Toast.makeText(getActivity(), ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }, new Response.ErrorListener() {
        public void onErrorResponse(VolleyError error) {

        }
    });


// Add the request to the RequestQueue.
        queue.add(jsonRequest);

}
}
