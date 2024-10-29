package com.example.planme.ui.views.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.planme.databinding.FragmentHomeBinding;
import com.example.planme.ui.adapters.RVGroupsAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        RVGroupsAdapter rvGroupsAdapter = new RVGroupsAdapter();
        rvGroupsAdapter.setOnClickListener((position, group) -> Toast.makeText(this.getContext(), group.getName(), Toast.LENGTH_SHORT).show());
        binding.rvAllGroups.setAdapter(rvGroupsAdapter);

        setUpRvGroups(rvGroupsAdapter);

        return binding.getRoot();
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