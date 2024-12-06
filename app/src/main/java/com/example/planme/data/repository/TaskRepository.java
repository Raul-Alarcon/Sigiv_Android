package com.example.planme.data.repository;

import androidx.annotation.NonNull;

import com.example.planme.data.models.FireBaseModel;
import com.example.planme.data.models.Task;
import com.example.planme.ui.models.FlightUI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TaskRepository {
    final DatabaseReference noteDbContext;
    public TaskRepository(DatabaseReference dbContext){
        this.noteDbContext = dbContext.child(FireBaseModel.task);
    }

    public void getAllByUser(String userId, Consumer<List<FlightUI>> onFinish){
        try {
            this.noteDbContext.child(userId)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            List<FlightUI> flights = new ArrayList<>();
                            snapshot.getChildren().forEach(dataSnapshot -> {
                                FlightUI task = dataSnapshot.getValue(FlightUI.class);
                                flights.add(task);
                            });
                            onFinish.accept(flights);
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


    public CompletableFuture<Boolean> addTask(String userId, FlightUI task){
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        try {
            String key = this.noteDbContext.child(userId).push().getKey();

            task.setId(key);

            if(!Objects.equals(key, "") && !Objects.isNull(key)){
                this.noteDbContext.child(userId)
                        .child(key).setValue(task).addOnCompleteListener(_task -> {
                            if(_task.isSuccessful()){
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
