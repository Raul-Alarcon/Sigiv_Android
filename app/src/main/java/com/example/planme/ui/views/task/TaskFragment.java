package com.example.planme.ui.views.task;

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
import com.example.planme.databinding.FragmentTaskBinding;
import com.example.planme.ui.adapters.RVNoteAdapter;

public class TaskFragment extends Fragment {
    FragmentTaskBinding binding;
    NavController navController;
    TaskViewModel taskViewModel;
    RVNoteAdapter rvNoteAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        this.binding = FragmentTaskBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.navController = Navigation.findNavController(view);

        this.rvNoteAdapter = new RVNoteAdapter();
        this.binding.rvAllNotes.setAdapter(rvNoteAdapter);

        if(this.binding != null){
            this.binding.btnFormNote.setOnClickListener( __ -> this.handleNavToNewNote());
        }

        if(this.taskViewModel != null) {
            this.taskViewModel.getNotesUI().observe(getViewLifecycleOwner(), rvNoteAdapter::setNotes);
        }

    }

    private void handleNavToNewNote(){
        this.navController.navigate(R.id.navigation_task_to_navigation_new_note);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

}