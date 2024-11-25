package com.example.planme.ui.views.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.planme.R;
import com.example.planme.databinding.GroupModalBinding;
import com.example.planme.ui.base.OnClickListenerBase;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.utils.ExceptionHelper;
import com.example.planme.utils.GenerateQRCode;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONObject;

import java.util.Objects;

public class FormGroup extends BottomSheetDialogFragment {
    private ActivityResultLauncher<ScanOptions> qrLauncher;
    GroupModalBinding binding;
    OnClickListenerBase listener;
    OnClickListenerBase onJoinListener;

    public interface FindListeners{
        void onFindOne(String query);
    }
    FindListeners findByCodeListener;
    GroupUI groupUI;

    public void setOnSaveClick(OnClickListenerBase listener){
        this.listener = listener;
    }
    public void setOnFindByCodeClick(FindListeners listener){
        this.findByCodeListener = listener;
    }

    public void setOnJoinClick(OnClickListenerBase listener){
        this.onJoinListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        qrLauncher = registerForActivityResult(new ScanContract(),result -> {
            if (result.getContents() != null) {
                // QR Code successfully scanned
                try {
                    handleQRResult(result.getContents());
                } catch (Exception e) {
                    ExceptionHelper.log(e);
                }
            }
        });
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

//        View parentView = (View) view.getParent();
//        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parentView);

//        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        behavior.setFitToContents(false); // Permite ajustar el contenido
//        behavior.setHalfExpandedRatio(0.6f); // Ratio del estado intermedio
        this.binding.formLayout.newContainer.setVisibility(View.GONE);
        this.binding.addByCodeLayout.setVisibility(View.GONE);
        this.binding.qrLayout.qrInfoContainer.setVisibility(View.GONE);

        this.binding.btnNew.setOnClickListener( __ -> {
            this.binding.qrLayout.qrInfoContainer.setVisibility(View.GONE);
            this.binding.formLayout.newContainer.setVisibility(View.VISIBLE);
            this.binding.addByCodeLayout.setVisibility(View.GONE);
        });

        this.binding.btnCode.setOnClickListener( __ -> {
            this.binding.qrLayout.qrInfoContainer.setVisibility(View.GONE);
            this.binding.formLayout.newContainer.setVisibility(View.GONE);
            this.binding.addByCodeLayout.setVisibility(View.VISIBLE);
        });

        // on close click
        this.binding.formLayout.btnClose.setOnClickListener(__ -> {
            this.dismiss();
            this.clearForm();
        });

        // on Save click
        this.binding.formLayout.btnSave.setOnClickListener(__ -> {
            if(this.listener != null){
                GroupUI groupUI = new GroupUI();
                groupUI.setName(String.valueOf(this.binding.formLayout.edtName.getText()));
                groupUI.setDescription(String.valueOf(this.binding.formLayout.edtDescription.getText()));
                this.listener.onClick(groupUI);
            }
        });

        this.binding.btnReadQr.setOnClickListener(__ -> {

            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.CAMERA}, 1);
            } else {
                launchQRScanner();
            }
        });

        this.binding.btnSendCode.setOnClickListener(__ -> {
            if(this.findByCodeListener != null){
                String code = Objects.requireNonNull(this.binding.codeInput.getText()).toString();
                this.findByCodeListener.onFindOne(code);
            }
        });

        this.binding.qrLayout.btnJoin.setOnClickListener( __ -> {
            if(this.groupUI != null && this.onJoinListener != null){
                this.onJoinListener.onClick(this.groupUI);
            }
        });
    }

    public void showInfoGroup(GroupUI groupUI){
        if(groupUI != null){
            this.groupUI = groupUI;
            this.binding.addByCodeLayout.setVisibility(View.GONE);
            this.binding.qrLayout.tvName.setText(groupUI.getName());
            this.binding.qrLayout.tvDescription.setText(groupUI.getDescription());

            TransitionManager.beginDelayedTransition((ViewGroup) this.binding.qrLayout.qrInfoContainer.getParent());
            this.binding.qrLayout.qrInfoContainer.setVisibility(View.VISIBLE);
        }
    }

    private void launchQRScanner() {
        ScanOptions options = new ScanOptions()
                .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                .setPrompt("Scan QR Code")
                .setCameraId(0) // Use back camera
                .setBeepEnabled(true)
                .setBarcodeImageEnabled(true)
                .setOrientationLocked(false);

        qrLauncher.launch(options);
    }

    private void handleQRResult(String qrContent) {
        try {
            GroupUI scannedGroup = GenerateQRCode.parseQRCode(qrContent);
            if (scannedGroup != null) {
                this.groupUI =  scannedGroup;
                this.binding.qrLayout.tvName.setText(scannedGroup.getName());
                this.binding.qrLayout.tvDescription.setText(scannedGroup.getDescription());
                this.binding.qrLayout.qrInfoContainer.setVisibility(View.VISIBLE);

            } else {
                Toast.makeText(getContext(),
                        "Invalid QR code format",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(getContext(),
                    "Error reading QR code: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void clearForm(){
        this.binding.formLayout.edtName.setText("");
        this.binding.formLayout.edtDescription.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}
