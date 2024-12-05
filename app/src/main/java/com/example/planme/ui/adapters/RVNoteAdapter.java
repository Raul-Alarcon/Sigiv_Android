package com.example.planme.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.R;
import com.example.planme.databinding.ItemNoteBinding;
import com.example.planme.ui.base.OnClickListenerBase;
import com.example.planme.ui.base.OnLongClickListenerBase;
import com.example.planme.ui.models.NoteUI;

import java.util.ArrayList;
import java.util.List;

public class RVNoteAdapter extends RecyclerView.Adapter<RVNoteAdapter.NoteHolder> {

    private List<NoteUI> noteUIS;
    private OnLongClickListenerBase onLongClickListener;
    private OnClickListenerBase onClickListener;
    public RVNoteAdapter(){
        this.noteUIS = new ArrayList<>();
    }
    public void setNotes(List<NoteUI> notes){
        this.noteUIS = notes;
        this.notifyDataSetChanged();
    }

    public void setOnLongClickListener(OnLongClickListenerBase onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }
    public void setOnClickListener(OnClickListenerBase onClickListener){
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        NoteUI noteUI = noteUIS.get(position);

        holder.itemView.setOnLongClickListener( __ -> {
            if(onLongClickListener != null){
                onLongClickListener.onLongClick(noteUI);
            }
            return true;
        });

        holder.itemView.setOnClickListener( __ -> {
            if(onClickListener != null){
                onClickListener.onClick(noteUI);
            }
        });

        holder.render(noteUI);
    }

    @Override
    public int getItemCount() {
        return this.noteUIS.size();
    }

    public static class NoteHolder extends RecyclerView.ViewHolder {
        ItemNoteBinding binding;
        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            this.binding = ItemNoteBinding.bind(itemView);
        }

        public void render(NoteUI noteUI){
            this.binding.tvTitle.setText(noteUI.getTitle());
            this.binding.tvDate.setText(noteUI.getDate());
            this.binding.tvShortContent.setText(noteUI.getShortContent());
        }
    }
}
