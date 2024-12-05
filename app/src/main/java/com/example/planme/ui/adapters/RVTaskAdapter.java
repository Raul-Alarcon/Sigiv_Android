package com.example.planme.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.R;
import com.example.planme.databinding.ItemTaskBinding;
import com.example.planme.ui.models.TaskUI;

import java.util.ArrayList;
import java.util.List;

public class RVTaskAdapter extends  RecyclerView.Adapter<RVTaskAdapter.TaskHolder> {
    private List<TaskUI> taskUIS;
    private ItemTaskBinding binding;
    public RVTaskAdapter(){
        this.taskUIS = new ArrayList<>();
    }

    public void setTaskUIS(List<TaskUI> taskUIS){
        this.taskUIS = taskUIS;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        TaskUI taskUI = taskUIS.get(position);
        holder.render(taskUI);
    }

    @Override
    public int getItemCount() {
        return taskUIS.size();
    }

    public static class TaskHolder extends RecyclerView.ViewHolder {

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void render(TaskUI taskUI){

        }
    }
}
