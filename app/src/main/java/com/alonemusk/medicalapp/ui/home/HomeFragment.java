package com.alonemusk.medicalapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.alonemusk.medicalapp.FirstActivity;
import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.EnterAdress.ItemFragment;
import com.alonemusk.medicalapp.ui.EnterAdress.dummy.DummyContent;
import com.alonemusk.medicalapp.ui.call.OrderByCallActivity;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment implements ItemFragment.OnListFragmentInteractionListener {

    private HomeViewModel homeViewModel;
    CardView cardView1,cardView2,myorder,cardView3,cardView4;
    ImageView addtocart;
    NavController navController;
TextView pincode;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        SearchView searchView=root.findViewById(R.id.search);
       addtocart=root.findViewById(R.id.gotocarthomeicon);
        RelativeLayout rv=root.findViewById(R.id.call);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(),OrderByCallActivity.class));
            }
        });
        cardView1=root.findViewById(R.id.home_card1);
        cardView2=root.findViewById(R.id.home_card2);
        cardView3=root.findViewById(R.id.home_card3);
        cardView4=root.findViewById(R.id.home_card4);
cardView3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        navController.navigate(R.id.action_navigation_home_to_navigation_cart);
    }
});
cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_home_to_navigation_cart);
            }
        });

myorder=root.findViewById(R.id.myorder);
        pincode=root.findViewById(R.id.pincode);
         navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
addtocart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        navController.navigate(R.id.action_navigation_home_to_navigation_cart);
    }
});
myorder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        navController.navigate(R.id.action_navigation_home_to_myOrderListFrag);
    }
});
cardView1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        navController.navigate(R.id.action_navigation_home_to_navigation_search);
    }
});
        pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "address", Toast.LENGTH_SHORT).show();
            }
        });


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.action_navigation_home_to_navigation_search);

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.allPresListFrag);
            }
        });
        TextView textView=root.findViewById(R.id.pincode);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_home_to_adress);
            }
        });
        TextView textView1=root.findViewById(R.id.deliverto);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_home_to_adress);
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