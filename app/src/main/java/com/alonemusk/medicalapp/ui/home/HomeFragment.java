package com.alonemusk.medicalapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.EnterAdress.ItemFragment;
import com.alonemusk.medicalapp.ui.EnterAdress.dummy.DummyContent;
import com.alonemusk.medicalapp.ui.MyOrders.MyOrders;
import com.alonemusk.medicalapp.ui.OrderByPres.OrderByPresActivity;
import com.alonemusk.medicalapp.ui.call.OrderByCallActivity;
import com.alonemusk.medicalapp.ui.models.ButtonHome;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemFragment.OnListFragmentInteractionListener {

    private HomeViewModel homeViewModel;
    CardView cardView1, cardView2, myorder, cardView3, cardView4;
    ImageView addtocart;
    NavController navController;
    TextView pincode;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setLayoutFrozen(true);

        List<ButtonHome> buttons = new ArrayList<ButtonHome>() {
            {
                add(new ButtonHome("Medicine",R.drawable.ic_add_circle_outline_black_24dp,null));
                add(new ButtonHome("Order",R.drawable.ic_call_black_24dp,OrderByCallActivity.class));
                add(new ButtonHome("Diagnostic",R.drawable.medical,null));
                add(new ButtonHome("Add prescription",R.drawable.ic_baseline_prescription_24,OrderByPresActivity.class));

            }
        };
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),buttons);
        recyclerView.setAdapter(adapter);
//        View view=root.findViewById(R.id.call);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(requireActivity(), OrderByCallActivity.class));
//            }
//        });
//        View pres=root.findViewById(R.id.adpres);
//        pres.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(requireActivity(), OrderByPresActivity.class));
//            }
//        });
        View order=root.findViewById(R.id.myorder);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), MyOrders.class));
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}