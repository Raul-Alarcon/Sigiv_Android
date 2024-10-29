package com.example.planme.ui.views.chat;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planme.R;
import com.example.planme.databinding.FragmentChatBinding;

public class ChatFragment extends Fragment {

    FragmentChatBinding binding;
    ChatViewModel mViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        binding = FragmentChatBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

}