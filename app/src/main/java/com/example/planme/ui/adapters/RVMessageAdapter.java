package com.example.planme.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.R;
import com.example.planme.data.models.Message;
import com.example.planme.databinding.ItemMessageBinding;
import com.example.planme.utils.DateFormatHelper;

import java.util.ArrayList;

public class RVMessageAdapter extends RecyclerView.Adapter<RVMessageAdapter.MessageHolder>{

    ArrayList<Message> messages;

    public RVMessageAdapter(){
        this.messages = new ArrayList<>();

    }

    public void setMessage(Message message){
        this.messages.add(message);
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
        Message message = this.messages.get(position);
        holder.render(message);
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {

        ItemMessageBinding binding;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemMessageBinding.bind(itemView);

        }

        public void render(Message message){
            this.binding.timeText.setText(DateFormatHelper.format(message.getDate(), "HH:mm a"));
            this.binding.messageText.setText(message.getContent());
            this.binding.userName.setText(message.getUser().getUserName());

        }
    }
}
