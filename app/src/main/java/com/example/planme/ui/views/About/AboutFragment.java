package com.example.planme.ui.views.About;

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

public class AboutFragment extends Fragment {

    FragmentAboutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.binding = FragmentAboutBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}





