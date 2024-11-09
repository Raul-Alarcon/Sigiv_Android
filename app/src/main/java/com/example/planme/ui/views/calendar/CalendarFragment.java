package com.example.planme.ui.views.calendar;

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

import com.example.planme.databinding.FragmentCalendarBinding;
import com.example.planme.databinding.FragmentHomeBinding;
import com.example.planme.ui.views.home.HomeViewModel;

public class CalendarFragment extends Fragment{

    private FragmentCalendarBinding binding;
    private HomeViewModel homeViewModel;
    NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        CalendarViewModel calendarViewModel = new ViewModelProvider(this)
                .get(CalendarViewModel.class);
        this.binding = FragmentCalendarBinding.inflate(inflater, container, false);

        return this.binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

}