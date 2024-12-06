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
import android.widget.Toast;

import com.example.planme.R;
import com.example.planme.data.models.Note;
import com.example.planme.databinding.FragmentTaskBinding;
import com.example.planme.ui.adapters.RVNoteAdapter;
import com.example.planme.ui.models.EntityUI;
import com.example.planme.ui.models.NoteUI;
import com.example.planme.utils.ExceptionHelper;

public class TaskFragment extends Fragment {
    FragmentTaskBinding binding;
    NavController navController;
    TaskViewModel taskViewModel;
    RVNoteAdapter rvNoteAdapter;
    ActionsNoteFragment actionsNoteFragment;
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
        this.actionsNoteFragment = new ActionsNoteFragment();
        this.actionsNoteFragment.setOnDeletedClickListener(this::handleDeleteNote);
        this.actionsNoteFragment.setOnEditClickListener(this::handleEditNote);

        this.rvNoteAdapter = new RVNoteAdapter();
        this.binding.rvAllNotes.setAdapter(rvNoteAdapter);

        this.rvNoteAdapter.setOnLongClickListener( entityUI -> {
            NoteUI noteUI = (NoteUI) entityUI;
            this.actionsNoteFragment.setNoteUISelected(noteUI);
            this.actionsNoteFragment.show(getParentFragmentManager(), "form_actions");
        });

        if(this.binding != null){
            this.binding.btnFormNote.setOnClickListener( __ -> this.handleNavToNewNote());
        }

        if(this.taskViewModel != null) {
            this.taskViewModel.getNotesUI().observe(getViewLifecycleOwner(), rvNoteAdapter::setNotes);
        }

        this.rvNoteAdapter.setOnClickListener(this::handleEditNote);

    }

    private void handleEditNote(EntityUI entityUI){
        try {
            this.actionsNoteFragment.dismiss();
            NoteUI noteUI = (NoteUI) entityUI;

            Bundle args = new Bundle();
            String noteString =  noteUI.toJsonString();

            args.putString("noteUI", noteString);
            navController.navigate(R.id.navigation_task_to_navigation_new_note, args);
        } catch (Exception e){
            ExceptionHelper.log(e);
        }
    }

    private void handleDeleteNote(EntityUI entityUI){
        try {
            NoteUI noteUI = (NoteUI) entityUI;
            this.taskViewModel.deleteNoteById(noteUI.getId())
                    .thenAccept( isDeleted -> {
                        if(isDeleted) {
                            this.actionsNoteFragment.dismiss();
                            Toast.makeText(getContext(), "successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }).exceptionally( throwable -> {
                        ExceptionHelper.log(new Exception(throwable.getMessage()));
                        return null;
                    });
        } catch (Exception e){
            ExceptionHelper.log(e);
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