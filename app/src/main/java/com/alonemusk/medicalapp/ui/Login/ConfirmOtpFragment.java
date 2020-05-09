package com.alonemusk.medicalapp.ui.Login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alonemusk.medicalapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmOtpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmOtpFragment extends Fragment {
    SendOtpViewModel sendOtpViewModel;
    public ConfirmOtpFragment() {
        // Required empty public constructor
    }


    public static ConfirmOtpFragment newInstance(String param1, String param2) {
        return new ConfirmOtpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_confirm_otp, container, false);
        final EditText editText=view.findViewById(R.id.code);
        Button confirm=view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtpViewModel.setCodeSent(editText.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        sendOtpViewModel= ViewModelProviders.of(requireActivity()).get(SendOtpViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }
}
