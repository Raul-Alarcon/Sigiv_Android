package com.example.planme.ui.views.Creadores;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planme.R;
import com.example.planme.databinding.FragmentAboutBinding;
import com.example.planme.databinding.FragmentCreadoresBinding;

import com.example.planme.R;

public class CreadoresFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_creadores, container, false);
    }
    FragmentCreadoresBinding binding;


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        this.binding = null;
    }

}