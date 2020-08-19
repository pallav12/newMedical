package com.alonemusk.medicalapp.ui.EnterAdress;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alonemusk.medicalapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressSelect extends Fragment {
Button Selectaddresbtn;

    public AddressSelect() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_address_select, container, false);
        Selectaddresbtn=v.findViewById(R.id.selectaddresbtn);
        Selectaddresbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return v;
    }

}
