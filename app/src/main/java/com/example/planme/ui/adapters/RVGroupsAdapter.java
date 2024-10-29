package com.example.planme.ui.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVGroupsAdapter extends RecyclerView.Adapter<RVGroupsAdapter.GroupHolder> {


    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class GroupHolder extends RecyclerView.ViewHolder {

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
