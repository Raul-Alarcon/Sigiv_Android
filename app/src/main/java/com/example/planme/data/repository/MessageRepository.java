package com.example.planme.data.repository;

import androidx.annotation.NonNull;

import com.example.planme.data.models.FireBaseModel;
import com.example.planme.data.models.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class MessageRepository {
    final DatabaseReference messageDbContext;
    public MessageRepository(DatabaseReference dbContext){
        this.messageDbContext = dbContext.child(FireBaseModel.message);
    }

    public void getAllMessageToGroup(String groupId, final Consumer<List<Message>> onSuccess, final Consumer<Exception> onFailure){
        Query query = this.messageDbContext
                .child(groupId)
                .orderByChild("groupId")
                .equalTo(groupId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Message> messages = new ArrayList<>();
                snapshot.getChildren().forEach(data -> {
                    Message message = data.getValue(Message.class);
                    if(message!=null){
                        messages.add(message);
                    }
                });
                onSuccess.accept(messages);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onFailure.accept(error.toException());
            }
        });
    }

    public void addMessageToGroup(String groupId,Message message, final Consumer<Exception> onFinish){
        try {
            String key = this.messageDbContext.child(groupId)
                    .push().getKey();

            if (String.valueOf(key).isEmpty()) throw new Exception("La llave no se pudo crear");
            if(key != null){
                message.setId(key);
                this.messageDbContext.child(groupId)
                        .child(key)
                        .setValue(message);
            }
        } catch (Exception exception){
            onFinish.accept(exception);
        }
    }

    public CompletableFuture<Boolean> deleteMessageToGroup(String groupId){
        CompletableFuture<Boolean> future = CompletableFuture.completedFuture(true);

        try {
            this.messageDbContext.child(groupId)
                    .removeValue()
                    .addOnCompleteListener( task -> {
                        if(task.isSuccessful()){
                            future.complete(true);
                        } else {
                            future.completeExceptionally(task.getException());
                        }
                    });
        } catch (Exception e){
            future.completeExceptionally(e);
        }

        return future;
    }

}

