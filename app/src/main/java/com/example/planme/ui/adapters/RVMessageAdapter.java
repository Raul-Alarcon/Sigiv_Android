package com.example.planme.ui.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.R;
import com.example.planme.databinding.ItemMessageBinding;
import com.example.planme.ui.models.CardMessageUI;

import java.util.ArrayList;

public class RVMessageAdapter extends RecyclerView.Adapter<RVMessageAdapter.MessageHolder>{

    ArrayList<CardMessageUI> messages;

    public RVMessageAdapter(){
        this.messages = new ArrayList<>();

    }

    public void setMessage(CardMessageUI message){
        this.messages.add(message);
        int position = this.messages.size() - 1;
        notifyItemChanged(position, message);
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);

        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        CardMessageUI message = this.messages.get(position);
        CardMessageUI lastMessage = null;
        if (position > 0){
            lastMessage = this.messages.get(position - 1);
        }

        holder.render(message, lastMessage);
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {

        ItemMessageBinding binding;
        Context context;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemMessageBinding.bind(itemView);
            context = itemView.getContext();
        }

        public void render(CardMessageUI message,  CardMessageUI lastMessage) {
            String lastUser = lastMessage != null ? lastMessage.getUserName() : "";

            if(message.isMe()){
                this.binding.myMessageLayout.setVisibility(View.VISIBLE);
                this.binding.otherMessageLayout.setVisibility(View.GONE);

                if(message.getUserName().equals(lastUser)){
                    this.binding.myMessageProperty.setVisibility(View.GONE);
                    this.binding.myProfileImg.setVisibility(View.INVISIBLE);
                }

                this.binding.myProfileImg.setImageResource(R.drawable.ic_launcher_foreground);
                this.binding.myUserTimeMessage.setText(message.getDate());
                this.binding.myMessageText.setText(message.getContent());
                this.binding.myUserName.setText(message.getUserName());

            } else {
                this.binding.myMessageLayout.setVisibility(View.GONE);
                this.binding.otherMessageLayout.setVisibility(View.VISIBLE);

                if(message.getUserName().equals(lastUser)){
                    this.binding.otherMessageProperty.setVisibility(View.GONE);
                    this.binding.otherProfileImg.setVisibility(View.INVISIBLE);
                }

                this.binding.otherProfileImg.setImageResource(R.drawable.ic_launcher_foreground);
                this.binding.otherUserTimeMessage.setText(message.getDate());
                this.binding.otherMessageText.setText(message.getContent());
                this.binding.otherUserName.setText(message.getUserName());
            }
        }


    }
}
