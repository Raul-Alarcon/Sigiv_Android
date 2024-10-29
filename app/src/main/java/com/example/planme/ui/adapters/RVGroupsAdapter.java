package com.example.planme.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.R;
import com.example.planme.data.models.Group;
import com.example.planme.data.models.Message;
import com.example.planme.databinding.ItemGroupBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RVGroupsAdapter extends RecyclerView.Adapter<RVGroupsAdapter.GroupHolder> {

    private ArrayList<Group> groups;
    private OnClickListener onClickListener;
    public RVGroupsAdapter() {
        this.groups = new ArrayList<>();
    }

    public void setGroups(ArrayList<Group> groups){
        this.groups = groups;
    }

    public interface OnClickListener {
         void onClick(int position, Group group);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group, parent, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        Group item = this.groups.get(position);
        holder.render(item, this.onClickListener);
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

        public void render(Group item, OnClickListener onClickListener){
            Message lastMessage = item.getLastMessage();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            //binding.imgGroup.setImageDrawable(); traer la imagen del grupo
            binding.tvTitleGroup.setText(item.getName());
            binding.tvLastMessage.setText( lastMessage != null ? lastMessage.getContent() : "Recently Created");
            binding.tvTimeLastMessageGroup.setText(lastMessage != null ?
                    simpleDateFormat.format(lastMessage.getDate()):
                        simpleDateFormat.format(item.getDate()));
            
            this.itemView.setOnClickListener( view -> {
                if(onClickListener != null) onClickListener.onClick(getAdapterPosition(), item);
            });
        }
    }
}
