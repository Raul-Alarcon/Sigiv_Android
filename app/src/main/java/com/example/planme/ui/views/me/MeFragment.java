package com.example.planme.ui.views.me;

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
import com.example.planme.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {

    NavController navController;
    FragmentMeBinding binding;
    MeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MeViewModel.class);
        this.binding = FragmentMeBinding.inflate(inflater, container, false);

        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.navController = Navigation.findNavController(view);

        this.binding.btnAbout.setOnClickListener(__ ->{
            navController.navigate(R.id.navigation_me_to_navigation_About);
        });
    }


}
