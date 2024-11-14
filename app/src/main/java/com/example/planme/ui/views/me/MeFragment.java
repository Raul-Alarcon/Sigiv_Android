package com.example.planme.ui.views.me;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.planme.R;
import com.example.planme.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {

    FragmentMeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MeViewModel viewModel = new ViewModelProvider(this).get(MeViewModel.class);
        this.binding = FragmentMeBinding.inflate(inflater, container, false);

        Button btnInvitacion = binding.btnInvitacion;
        Button btnAbout = binding.btnAbout;
        Button btnCerrarSesion = binding.btnCerrarSesion;

        btnInvitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Invitación", Toast.LENGTH_SHORT).show();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "About", Toast.LENGTH_SHORT).show();
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Cerrar sesión", Toast.LENGTH_SHORT).show();
                // Aquí puedes añadir un Intent para cerrar sesión o volver al login, si es necesario
            }
        });

        return this.binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}
