package com.alonemusk.medicalapp.ui.Checkout;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonemusk.medicalapp.R;

public class PaymentMethodFragment extends Fragment {
CardView cod;
    private PaymentMethodViewModel mViewModel;

    public static PaymentMethodFragment newInstance() {
        return new PaymentMethodFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.payment_method_fragment, container, false);
       cod=v.findViewById(R.id.cod);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
       cod.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               navController.navigate(R.id.action_paymentMethodFragment_to_navigation_home);
reload();
           }
       });
       return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    public void reload() {
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PaymentMethodViewModel.class);
        // TODO: Use the ViewModel
    }

}
