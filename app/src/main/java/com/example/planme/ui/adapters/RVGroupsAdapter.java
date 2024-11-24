package com.example.planme.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.R;
import com.example.planme.databinding.ItemGroupBinding;
import com.example.planme.ui.base.OnClickListener;
import com.example.planme.ui.models.GroupUI;

import java.util.ArrayList;
import java.util.List;

public class RVGroupsAdapter extends RecyclerView.Adapter<RVGroupsAdapter.GroupHolder> {

    private List<GroupUI> groups;
    private OnClickListener onClickListener;
    public RVGroupsAdapter() {
        this.groups = new ArrayList<>();
    }

    public void setGroups(List<GroupUI> groups){
        this.groups = groups;
        this.notifyDataSetChanged();
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
        GroupUI item = this.groups.get(position);
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

        public void render(GroupUI item, OnClickListener onClickListener){
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a", new Locale("es", "ES"));

            //binding.imgGroup.setImageDrawable(); traer la imagen del grupo
            binding.tvTitleGroup.setText(item.getName());

            binding.tvLastMessage.setText(!item.getContentLastMessage().isEmpty() ?
                    item.getContentLastMessage() : "Recently Created");


            binding.tvTimeLastMessageGroup.setText(!item.getDateLastMessage().isEmpty()?
                    item.getDateLastMessage():
                    item.getDate());
            
            this.itemView.setOnClickListener( view -> {
                if(onClickListener != null) onClickListener.onClick(getAdapterPosition(), item);
            });
        }
    }
}
