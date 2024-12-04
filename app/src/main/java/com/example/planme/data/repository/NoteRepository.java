package com.example.planme.data.repository;

import com.example.planme.data.models.FireBaseModel;
import com.example.planme.data.models.Note;
import com.example.planme.utils.DateFormatHelper;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class NoteRepository {
    final DatabaseReference noteDbContext;
    public NoteRepository(DatabaseReference dbContext){
        this.noteDbContext = dbContext.child(FireBaseModel.note);
    }


    public CompletableFuture<Boolean> addNote(String userId, Note note){
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        try {
            String key = this.noteDbContext.child(userId).push().getKey();

            note.setId(key);
            note.setDate(DateFormatHelper.getCurrentDateTime());

            if(Objects.equals(key, "")){
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
