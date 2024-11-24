package com.example.planme.data.repository;

import androidx.annotation.NonNull;

import com.example.planme.data.models.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GroupRepository  {
    final DatabaseReference dbRef;
    final static String model = "groups";
    public GroupRepository(DatabaseReference databaseReference ) {
        this.dbRef = databaseReference;
    }

    public void getGroupsByUserId(String currentUserId, final BiConsumer<List<Group>, Exception> onFinish) {
        Query query = this.dbRef.child(model)
                .orderByChild("userId").equalTo(currentUserId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Group> _groups = new ArrayList<>();
                dataSnapshot.getChildren().forEach( _data -> {
                    Group group = _data.getValue(Group.class);
                    if (group != null) {
                        _groups.add(group);
                    }
                });
                onFinish.accept(_groups, null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                onFinish.accept(null, databaseError.toException());
            }
        });
    }

    public void add(Group group, final Consumer<Exception> onFinish){
        try {
            String key = this.dbRef.child(model)
                    .push().getKey();
            group.setId(key);


            if (!String.valueOf(key).isEmpty()){
                throw new Exception("La clave no pudo ser creada");
            }

            assert key != null;
            this.dbRef.child(model)
                    .child(key)
                    .setValue(group);

        } catch (Exception e){
            onFinish.accept(e);
        }

    }

}
