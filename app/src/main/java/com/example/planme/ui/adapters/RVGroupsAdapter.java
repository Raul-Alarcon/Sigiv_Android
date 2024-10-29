package com.example.planme.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.R;
import com.example.planme.data.models.Group;
import com.example.planme.data.models.Message;
import com.example.planme.databinding.ItemGroupBinding;

import java.util.ArrayList;

public class RVGroupsAdapter extends RecyclerView.Adapter<RVGroupsAdapter.GroupHolder> {

    ArrayList<Group> groups;
    public RVGroupsAdapter(ArrayList<Group> groups) {
        this.groups = groups;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        Group item = this.groups.get(position);
        holder.render(item);
    }

    @Override
    public int getItemCount() {
        return this.groups.size();
    }

    public static class GroupHolder extends RecyclerView.ViewHolder {

        ItemGroupBinding binding;
        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemGroupBinding.bind(itemView);
        }

        public void render(Group item){
            Message lastMessage = item.getLastMessage();
            //binding.imgGroup.setImageDrawable(); traer la imagen del grupo
            binding.tvTitleGroup.setText(item.getName());
            binding.tvLastMessage.setText(lastMessage.getContent());
            binding.tvTimeLastMessageGroup.setText(lastMessage.getDate().toString());
        }
    }
}
