package com.alonemusk.medicalapp.ui.EnterAdress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.classes.JsonParsing;
import com.alonemusk.medicalapp.ui.Cart.CartAdapter;
import com.alonemusk.medicalapp.ui.Cart.CartDetails;
import com.alonemusk.medicalapp.ui.EnterAdress.dummy.DummyContent;
import com.alonemusk.medicalapp.ui.EnterAdress.dummy.DummyContent.DummyItem;
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
import java.util.List;

import static android.app.Activity.RESULT_OK;



    public class ItemFragment extends Fragment implements MyItemRecyclerViewAdapter.onMenuClicked{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    List<Note>noted;
    // TODO: Customize parameters
    private int mColumnCount = 1;

    NoteViewmodel noteViewModel;
    private OnListFragmentInteractionListener mListener;
     MyItemRecyclerViewAdapter myItemRecyclerViewAdapter=null;
        ArrayList<Note> addresslist;
        /**
     *
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     *
     **/
    public ItemFragment() {
    }



    @SuppressWarnings("unused")

    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        addresslist=new ArrayList<>();
        RecyclerView recyclerView=view.findViewById(R.id.list);

        noteViewModel= ViewModelProviders.of(this).get(NoteViewmodel.class);
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("somParam", "someValue")
//                .build();
//        Request request = new Request.Builder()
//                .url("http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/user/insert-address")
//                .post(requestBody)
//
//                .build();
        String url="http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/user/fetch-address-userid/"+10001;


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
                noteViewModel.deleteAll();
                for(JSONObject obj:jsonObjects){
                    Note object = gson.fromJson(String.valueOf(obj), Note.class);
                    addresslist.add(object);
                    noteViewModel.Insert(object);
                }





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
       myItemRecyclerViewAdapter=new MyItemRecyclerViewAdapter(this);
        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(myItemRecyclerViewAdapter);

        }




        noteViewModel.getAllnote().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Log.d("notes notes notes",notes.toString());
                noted=notes;
                myItemRecyclerViewAdapter.setNotes(notes);

            }
        });
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        FloatingActionButton floatingActionButton=view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_home_to_adress_Form);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMenuClicked(final int i, int j) {

        switch (j) {
            case 1: {
               // delete(noted.get(i).getAddress_id());
                noteViewModel.delete(noted.get(i));
                break;
            }
            case 2: {
                String address = noted.get(i).getAddress();
                String city = noted.get(i).getCity();
                String state = noted.get(i).getState();

                Bundle bundle = new Bundle();
                bundle.putString("address", address);
                bundle.putInt("position", i);
                bundle.putString("city", city);
                bundle.putString("state", state);
                final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

                navController.navigate(R.id.action_navigation_home_to_edit_Form, bundle);
            break;
            }
        }


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
    }
