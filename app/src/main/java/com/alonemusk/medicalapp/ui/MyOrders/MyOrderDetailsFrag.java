package com.alonemusk.medicalapp.ui.MyOrders;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonemusk.medicalapp.R;

public class MyOrderDetailsFrag extends Fragment {

    private MyOrderDetailsViewModel mViewModel;

    public static MyOrderDetailsFrag newInstance() {
        return new MyOrderDetailsFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_order_details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyOrderDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}
