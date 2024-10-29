package com.example.planme.ui.views.me;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planme.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {

    FragmentMeBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MeViewModel viewModel = new ViewModelProvider(this).get(MeViewModel.class);
        this.binding = FragmentMeBinding.inflate(inflater, container, false);

        return this.binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

}