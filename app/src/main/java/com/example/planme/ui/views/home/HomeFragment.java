package com.example.planme.ui.views.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.planme.R;
import com.example.planme.data.models.Group;
import com.example.planme.databinding.FragmentHomeBinding;
import com.example.planme.ui.adapters.RVGroupsAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    NavController navController;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        RVGroupsAdapter rvGroupsAdapter = new RVGroupsAdapter();
        rvGroupsAdapter.setOnClickListener((position, group) -> {
            this.navController.navigate(R.id.navigation_home_to_navigation_chat);
        });
        binding.rvAllGroups.setAdapter(rvGroupsAdapter);
        setUpRvGroups(rvGroupsAdapter);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        this.binding.btnFormGroup.setOnClickListener(__ -> {
            FormGroup addGroupForm = new FormGroup();
            addGroupForm.show(getParentFragmentManager(), "add_group");
        });
    }

    private void setUpRvGroups(RVGroupsAdapter rvGroupsAdapter) {
        this.homeViewModel.getGroups().observe(getViewLifecycleOwner(),rvGroupsAdapter::setGroups);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}