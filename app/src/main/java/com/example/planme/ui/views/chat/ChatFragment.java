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
import com.example.planme.ui.adapters.RVMessageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChatFragment extends Fragment {

    FragmentChatBinding binding;
    ChatViewModel chatViewModel;
    BottomNavigationView bottomNavigationView;
    String groupId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            groupId = getArguments().getString("groupId");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        binding = FragmentChatBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = requireActivity().findViewById(R.id.nav_view);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        RVMessageAdapter messageAdapter = new RVMessageAdapter();
        this.binding.rvChatMessage.setAdapter(messageAdapter);

        this.binding.btnSend.setOnClickListener( __ -> {
            String content = this.binding.etvMessage.getText().toString();
            if(!content.isEmpty()){
                this.chatViewModel.sendMessage(groupId,content);
                this.binding.etvMessage.setText("");
            }
        });

        this.chatViewModel.getMessageGroup(groupId).observe(getViewLifecycleOwner(), messageUIS -> {
            messageUIS.forEach(messageAdapter::setMessage);
            if (messageAdapter.getItemCount() > 0){
                this.binding.rvChatMessage.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }

}