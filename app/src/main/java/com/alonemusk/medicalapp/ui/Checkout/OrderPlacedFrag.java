package com.alonemusk.medicalapp.ui.Checkout;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alonemusk.medicalapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderPlacedFrag extends Fragment {
TextView TrackyourOrder;

    public OrderPlacedFrag() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        reload();
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_order_placed, container, false);
       TrackyourOrder=v.findViewById(R.id.trackyourOrder);
       TrackyourOrder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
               navController.navigate(R.id.action_orderPlacedFrag_to_navigation_home);
               reload();
           }
       });

       return v;
    }



    public void reload() {
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    } }