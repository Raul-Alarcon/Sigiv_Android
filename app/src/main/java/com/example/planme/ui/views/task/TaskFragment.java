package com.example.planme.ui.views.task;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planme.R;
import com.example.planme.databinding.FragmentTaskBinding;

public class TaskFragment extends Fragment {
    FragmentTaskBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        TaskViewModel taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        this.binding = FragmentTaskBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

}