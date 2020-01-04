package com.alonemusk.medicalapp.ui.NeedHelp;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonemusk.medicalapp.R;

public class NeedHelpFrag extends Fragment {

    private NeedHelpViewModel mViewModel;

    public static NeedHelpFrag newInstance() {
        return new NeedHelpFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.need_help_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NeedHelpViewModel.class);
        // TODO: Use the ViewModel
    }

}
