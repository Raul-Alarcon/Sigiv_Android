package com.example.planme.ui.views.home;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.planme.R;
import com.example.planme.databinding.GroupModalBinding;
import com.example.planme.ui.base.OnClickListenerBase;
import com.example.planme.ui.models.GroupUI;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FormGroup extends BottomSheetDialogFragment {

    GroupModalBinding binding;
    OnClickListenerBase listener;

    public void setOnSaveClick(OnClickListenerBase listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = GroupModalBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View parentView = (View) view.getParent();
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parentView);

        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setFitToContents(false); // Permite ajustar el contenido
        behavior.setHalfExpandedRatio(0.6f); // Ratio del estado intermedio
        // on close click
        this.binding.btnClose.setOnClickListener(__ -> {
            this.dismiss();
            this.clearForm();
        });

        // on Save click
        this.binding.btnSave.setOnClickListener(__ -> {
            if(this.listener != null){
                GroupUI groupUI = new GroupUI();
                groupUI.setName(String.valueOf(this.binding.edtName.getText()));
                groupUI.setDescription(String.valueOf(this.binding.edtDescription.getText()));
                this.listener.onClick(groupUI);
            }
        });
    }

    public void clearForm(){
        this.binding.edtName.setText("");
        this.binding.edtDescription.setText("");
    }



}
