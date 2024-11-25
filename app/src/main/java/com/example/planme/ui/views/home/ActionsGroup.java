package com.example.planme.ui.views.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.planme.databinding.FragmentInfoGroupBinding;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.utils.ExceptionHelper;
import com.example.planme.utils.GenerateQRCode;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActionsGroup extends BottomSheetDialogFragment {
    FragmentInfoGroupBinding binding;
    private GroupUI groupUI;
    public void setGroupSelected(GroupUI groupUI) {
        this.groupUI = groupUI;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentInfoGroupBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.infoLayout.infoContainer.setVisibility(View.VISIBLE);
        this.binding.qrCodeLayout.qrCodeContainer.setVisibility(View.GONE);

        if(this.groupUI != null){
            this.binding.infoLayout.groupName.setText(this.groupUI.getName());
            this.binding.infoLayout.groupDescription.setText(this.groupUI.getDescription());
        }

        this.binding.btnInfo.setOnClickListener( __ -> {
            this.binding.infoLayout.infoContainer.setVisibility(View.VISIBLE);
            this.binding.qrCodeLayout.qrCodeContainer.setVisibility(View.GONE);

        });

        this.binding.btnQr.setOnClickListener( __ -> {
            this.binding.infoLayout.infoContainer.setVisibility(View.GONE);
            this.binding.qrCodeLayout.qrCodeContainer.setVisibility(View.VISIBLE);

            this.binding.qrCodeLayout.codeInput.setText(this.groupUI.getCode());
            try {
                Bitmap img = GenerateQRCode.qRCode(this.groupUI);
                this.binding.qrCodeLayout.qrCodeImage.setImageBitmap(img);
            } catch (Exception e) {
                ExceptionHelper.log(e);
            }


        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}
