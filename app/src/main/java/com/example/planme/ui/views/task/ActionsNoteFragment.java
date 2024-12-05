package com.example.planme.ui.views.task;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planme.R;
import com.example.planme.databinding.FragmentActionsNoteBinding;
import com.example.planme.ui.base.OnClickListenerBase;
import com.example.planme.ui.models.NoteUI;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class ActionsNoteFragment extends BottomSheetDialogFragment {
    FragmentActionsNoteBinding binding;
    NoteUI noteUISelected;
    private OnClickListenerBase onDeletedClickListener;
    private OnClickListenerBase onEditClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentActionsNoteBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(this.binding != null) {
            this.binding.btnDelete.setOnClickListener(__ -> {
                if (this.onDeletedClickListener != null){
                    this.onDeletedClickListener.onClick(this.noteUISelected);
                }
            });

            this.binding.btnEdit.setOnClickListener(__ -> {
                if (this.onEditClickListener != null){
                    this.onEditClickListener.onClick(this.noteUISelected);
                }
            });
        }
    }
    public void setOnDeletedClickListener(OnClickListenerBase onDeletedClickListener){
        this.onDeletedClickListener = onDeletedClickListener;
    }
    public void setOnEditClickListener(OnClickListenerBase onEditClickListener){
        this.onEditClickListener = onEditClickListener;
    }

    public void setNoteUISelected(NoteUI noteUI){
        this.noteUISelected = noteUI;
    }

}