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

public class MyOrderListFrag extends Fragment {

    private MyOrderListViewModel mViewModel;

    public static MyOrderListFrag newInstance() {
        return new MyOrderListFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_order_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyOrderListViewModel.class);
        // TODO: Use the ViewModel
    }

}
