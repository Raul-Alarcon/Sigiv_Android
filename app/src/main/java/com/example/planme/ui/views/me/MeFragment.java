package com.example.planme.ui.views.me;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.planme.R;
import com.example.planme.databinding.FragmentMeBinding;
import com.example.planme.utils.ExceptionHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MeFragment extends Fragment {

    NavController navController;
    FragmentMeBinding binding;
    MeViewModel meViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        meViewModel = new ViewModelProvider(this).get(MeViewModel.class);
        this.binding = FragmentMeBinding.inflate(inflater, container, false);

        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.navController = Navigation.findNavController(view);

        this.binding.btnAbout.setOnClickListener(__ ->{
            navController.navigate(R.id.navigation_me_to_navigation_About);
        });

        if(meViewModel != null){
            this.meViewModel.getUser().observe(getViewLifecycleOwner(), userUI -> {
                if(userUI != null){
                    this.binding.tvUser.setText(userUI.getName());
                    this.binding.tvEmail.setText(userUI.getEmail());

                    Glide.with(requireContext())
                            .load(userUI.getUrlImg())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_notifications_black_24dp)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(this.binding.imgUser);

                }
            });
        }

        if(binding != null){
            this.binding.btnLogOut.setOnClickListener( __ -> this.handleLogOut());
        }
    }

    private void handleLogOut(){
        try {
            this.meViewModel.signOut();
            GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();

            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.mobile_navigation, true)
                    .build();

            navController.navigate(R.id.navigation_login, null, navOptions);

        } catch (Exception e){
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            ExceptionHelper.log(e);
        }

    }


}
