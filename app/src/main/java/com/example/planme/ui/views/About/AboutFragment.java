package com.example.planme.ui.views.About;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planme.R;
import com.example.planme.databinding.FragmentAboutBinding;
import com.example.planme.ui.views.Creadores.CreadoresFragment;

public class AboutFragment extends Fragment {

    FragmentAboutBinding binding;
    NavController controller;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.binding = FragmentAboutBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.controller = Navigation.findNavController(view);
        this.binding.creatorsButton.setOnClickListener(__->{
            this.controller.navigate(R.id.action_navigation_About_to_navigation_Creadores);
        });

    }
}





