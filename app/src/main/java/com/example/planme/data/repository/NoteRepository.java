package com.example.planme.data.repository;

import androidx.annotation.NonNull;

import com.example.planme.data.models.FireBaseModel;
import com.example.planme.data.models.Note;
import com.example.planme.utils.DateFormatHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class NoteRepository {
    final DatabaseReference noteDbContext;
    public NoteRepository(DatabaseReference dbContext){
        this.noteDbContext = dbContext.child(FireBaseModel.note);
    }

    public void getAllByUser(String userId, Consumer<List<Note>> onFinish){
        try {
            this.noteDbContext.child(userId)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            List<Note> notes = new ArrayList<>();
                            snapshot.getChildren().forEach(dataSnapshot -> {
                                Note note = dataSnapshot.getValue(Note.class);
                                notes.add(note);
                            });
                            onFinish.accept(notes);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            onFinish.accept(null);
                        }
                    });
        } catch (Exception e){
            onFinish.accept(null);
        }
    }


    public CompletableFuture<Boolean> addNote(String userId, Note note){
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        try {
            String key = this.noteDbContext.child(userId).push().getKey();

            note.setId(key);
            note.setDate(DateFormatHelper.getCurrentDateTime());

            if(!Objects.equals(key, "") && !Objects.isNull(key)){
                this.noteDbContext.child(userId)
                        .child(key).setValue(note).addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                future.complete(true);

                            } else {
                                future.complete(false);
                            }
                        });
            }

        } catch (Exception e){
            future.completeExceptionally(e);
        }

        return future;
    }

}
