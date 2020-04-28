package com.alonemusk.medicalapp.ui.Cart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.classes.JsonParsing;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Cart.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cart extends Fragment implements Go_TO_Cart {
    ArrayList<CartDetails> cartDetails;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<CartDetails> cartDetailsArrayList;
    RecyclerView Cart_Recycler_View;
    Go_TO_Cart go_to_cart;
    Button PlaceOrderBtn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Cart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cart.
     */
    // TODO: Rename and change types and number of parameters
    public static Cart newInstance(String param1, String param2) {
        Cart fragment = new Cart();
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
        cartDetails = new ArrayList<CartDetails>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        ImageView imageView = v.findViewById(R.id.image);
        Cart_Recycler_View = v.findViewById(R.id.cart_recyclerView);
        PlaceOrderBtn = v.findViewById(R.id.placeorderbutton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.action_navigation_cart_to_navigation_after_search);

            }
        });


        PlaceOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                //  b.p"arraylist", cartDetailsArrayList);
                navController.navigate(R.id.action_navigation_cart_to_confirm_order_fregment2);

            }
        });
        cartDetailsArrayList = new ArrayList<>();
        go_to_cart = this;
        // RecyclerView recyclerView=v.findViewById(R.id.recyclerView);
        volly(go_to_cart);
        Cart_Recycler_View.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    private void volly(final Go_TO_Cart go_to_cart) {
        String url = "http://ec2-13-235-73-199.ap-south-1.compute.amazonaws.com:3000/cart/get-user-cart/mahendra";


        final RequestQueue queue = Volley.newRequestQueue(getActivity());

        //  Toast.makeText(Mutual_details.this,""+url,Toast.LENGTH_SHORT).show();


        final StringRequest jsonRequest = new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Toast.makeText(getActivity(), "nothing "+response, Toast.LENGTH_SHORT).show();
                //    Toast.makeText(getActivity(), "cart fetched this", Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                cartDetails.clear();
                ArrayList<JSONObject> jsonObjects = JsonParsing.parsejson(response);

                for (JSONObject obj : jsonObjects) {
                    CartDetails object = gson.fromJson(String.valueOf(obj), CartDetails.class);
                    cartDetails.add(object);
                }
                if (cartDetails.size() > 0) {
                    Cart_Recycler_View.setAdapter(new CartAdapter(getActivity(), cartDetails, go_to_cart));
                }

                Toast.makeText(getActivity(), "nothing is there in cart", Toast.LENGTH_SHORT).show();


            }


        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(Mutual_details.this,"check internet connection"+error,Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "cart not fetched   " + error.getMessage(), Toast.LENGTH_SHORT).show();

                cartDetails.clear();

                Cart_Recycler_View.setAdapter(new CartAdapter(getActivity(), cartDetails, go_to_cart));


                Toast.makeText(getActivity(), "nothing is there in cart", Toast.LENGTH_SHORT).show();

            }

        }) {
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                //  params.put("Content-Type", " text/javascript");
                params.put("Content-Type", "application/json");
                return params;
            }
        };
// Add the request to the RequestQueue.
        queue.add(jsonRequest);
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
        navController.navigate(R.id.action_navigation_cart_to_navigation_after_search);

    }

    @Override
    public void OnDeleteClicked(int position) {
        deletefromcart(cartDetails.get(position).getUser_id(), cartDetails.get(position).getMedicine_id());
    }

    public void deletefromcart(String user_id, int medicine_id) {


        Toast.makeText(getActivity(), "in addtocart", Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // JSONObject urlf = new JSONObject(data);
        JSONObject data2 = new JSONObject();


        final JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.DELETE
                , "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/cart/delete-item-cart/" + user_id + "/" + medicine_id, data2,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //  Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                        volly(go_to_cart);
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
    public void OnIncreaseClicked(int position) {
        if (cartDetails.get(position).getQuantity() < 10) {
            //  cartDetails.get(position).setQuantity(cartDetails.get(position).getQuantity()+1);
            increaseinserver(cartDetails.get(position).getUser_id(), cartDetails.get(position).getMedicine_id(), cartDetails.get(position).getQuantity() + 1);
        } else {
            Toast.makeText(getActivity(), "you can not add more then 10", Toast.LENGTH_SHORT).show();
        }
    }

    public void increaseinserver(String user_id, int medicine_id, int qt) {


        Toast.makeText(getActivity(), "in addtocart", Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // JSONObject urlf = new JSONObject(data);
        JSONObject data2 = new JSONObject();

        try {
            data2.put("user_id", user_id);
            data2.put("medicine_id", medicine_id);
            data2.put("quantity", qt);


        } catch (Exception e) {

        }


        final JsonObjectRequest putRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST
                , "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/cart/insert-medicine-cart", data2,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //  Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                        volly(go_to_cart);
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
    public void OnDecreaseClicked(int position) {
        if (cartDetails.get(position).getQuantity() == 1) {
            Toast.makeText(getActivity(), "minimum 1 is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cartDetails.get(position).getQuantity() > 2) {
            //   cartDetails.get(position).setQuantity(cartDetails.get(position).getQuantity()-1);
            decreaseQuantityfromServer(cartDetails.get(position).getUser_id(), cartDetails.get(position).getMedicine_id(), cartDetails.get(position).getQuantity() - 1);
        }

    }

    public void decreaseQuantityfromServer(String user_id, int medicine_id, int qt) {


        Toast.makeText(getActivity(), "in addtocart", Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // JSONObject urlf = new JSONObject(data);
        JSONObject data2 = new JSONObject();

        try {
            data2.put("user_id", user_id);
            data2.put("medicine_id", medicine_id);
            data2.put("quantity", qt);


        } catch (Exception e) {

        }

        final JsonObjectRequest putRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST
                , "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/cart/insert-medicine-cart", data2,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //  Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                        volly(go_to_cart);
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
}
