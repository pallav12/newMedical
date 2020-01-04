package com.alonemusk.medicalapp.ui.OrderByPres;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllPresListFrag extends Fragment implements View.OnContextClickListener {

    private AllPresListViewModel mViewModel;
RecyclerView allpresrecyclerview;
    RecyclerView.LayoutManager layoutManager_noti;
FloatingActionButton floatingActionButton;
    public static AllPresListFrag newInstance() {
        return new AllPresListFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.all_pres_list_fragment, container, false);
        allpresrecyclerview=root.findViewById(R.id.all_pres_list_Recyclerview);
       floatingActionButton=root.findViewById(R.id.floatingactionbuttonid);
        layoutManager_noti = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
           allpresrecyclerview.setLayoutManager(layoutManager_noti);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "floating clicked ", Toast.LENGTH_SHORT).show();

        navController.navigate(R.id.action_allPresListFrag_to_submitPresDetailsFrag);
    }
});
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AllPresListViewModel.class);
        // TODO: Use the ViewModel




        ALL_Pros_RecyclerAdapter adapter1;
        adapter1 = new ALL_Pros_RecyclerAdapter(getActivity());
        allpresrecyclerview.setAdapter(adapter1);
    }

    @Override
    public boolean onContextClick(View view) {
        switch (view.getId()){
            case R.id.floatingactionbuttonid:
            {

                return true;
            }
        }





        return false;
    }
}
