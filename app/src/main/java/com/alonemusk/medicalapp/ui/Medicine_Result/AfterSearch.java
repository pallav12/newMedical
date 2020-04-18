package com.alonemusk.medicalapp.ui.Medicine_Result;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.classes.JsonParsing;
import com.alonemusk.medicalapp.ui.Search.SearchAdapter;
import com.alonemusk.medicalapp.ui.Search.SearchMedicine;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


public class AfterSearch extends Fragment implements AfterSearchAdapter.GoTOCart, AdapterView.OnItemSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView Medicine_name_textview;
    RelativeLayout relativeLayout;
    int medicine_id;
    Button addtocartbtn;
    MedicineAttributes object;
    int user_id;
    TextView Price;
    CardView cardViewtocart,go_to_cart_from_searchcard;
    Spinner spinner;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AfterSearch() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static AfterSearch newInstance(String param1, String param2) {
        AfterSearch fragment = new AfterSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

         medicine_id=getArguments().getInt("medicine_id");
         user_id=getArguments().getInt("user_id");
        Toast.makeText(getActivity(), ""+medicine_id+user_id, Toast.LENGTH_SHORT).show();


   }
// <androidx.recyclerview.widget.RecyclerView
//    android:id="@+id/recyclerView"
//    android:layout_below="@+id/result"
//    android:layout_width="match_parent"
//    tools:listitem="@layout/item_medicine"
//    android:layout_height="match_parent">
//
//    </androidx.recyclerview.widget.RecyclerView>

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_after_search, container, false);
        spinner=v.findViewById(R.id.quantity);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.medicine_quantity,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
Price=v.findViewById(R.id.price);
        ImageView imageView=v.findViewById(R.id.image);
        cardViewtocart=v.findViewById(R.id.go_to_cart_from_product);
        go_to_cart_from_searchcard=v.findViewById(R.id.go_to_cart_from_search);
go_to_cart_from_searchcard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
     //   getActivity().onBackPressed();
    }
});
        ImageView cartclicked=v.findViewById(R.id.clicked_on_cart);
        Medicine_name_textview=v.findViewById(R.id.medicine_name_id);
       relativeLayout=v.findViewById(R.id.medicine_details_layout);
       addtocartbtn=v.findViewById(R.id.addtocartbtn);
     //  spinner.setOnItemSelectedListener(this);
        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToCart();
            }
        });
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.action_navigation_after_search_to_navigation_search);

            }
        });
        cardViewtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_after_search_to_navigation_cart);
            }
        });
        cartclicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_after_search_to_navigation_cart);
            }
        });
//
//        RecyclerView recyclerView=v.findViewById(R.id.recyclerView);
//        ArrayList<String>str=new ArrayList<>();
//        str.add("hello");
//        str.add("hello");
//        str.add("hello");
//        str.add("hello");
//        str.add("hello");
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new AfterSearchAdapter(str,getActivity(),this));



        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        OkHttpClient client = new OkHttpClient();

        String url = "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/fetch-medicine/"+medicine_id;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "in funtion "+e);
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();

                            ArrayList<JSONObject>jsonObjects= JsonParsing.parsejson(myResponse);

                            for(JSONObject obj:jsonObjects){
                                 object = gson.fromJson(String.valueOf(obj), MedicineAttributes.class);
                                Medicine_name_textview.setText(object.getMedicine_name());
//Price.setText(object.getPrice()+" INR");
                                    relativeLayout.setVisibility(View.VISIBLE);
                            }
                            Log.d(TAG, "in funtion ");
                        }
                    });
                }

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void gotocart(int position) {
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_navigation_after_search_to_navigation_cart);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        switch (view.getId()){
//            case R.id.quantity:
//            {
                String text=adapterView.getItemAtPosition(i).toString();
//                Price.setText(""+Integer.parseInt(text)*Integer.parseInt(object.getPrice()));
//                Toast.makeText(getActivity(), "prive    "+object.getPrice(), Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void AddToCart(){




            Toast.makeText(getActivity(), "in addtocart", Toast.LENGTH_SHORT).show();
            RequestQueue queue = Volley.newRequestQueue(getActivity());

            // JSONObject urlf = new JSONObject(data);
            JSONObject data2 = new JSONObject();

            try{
                data2.put("user_id",user_id);
                data2.put("medicine_id",object.getMedicine_id());
                data2.put("quantity",Integer.parseInt(spinner.getSelectedItem().toString()));




            }catch(Exception e){

            }


            final JsonObjectRequest putRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST
                    , "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/cart/insert-medicine-cart", data2,
                    new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
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






















    }

