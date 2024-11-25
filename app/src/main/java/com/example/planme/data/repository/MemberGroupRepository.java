package com.example.planme.data.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.planme.data.models.FireBaseModel;
import com.example.planme.data.models.Group;
import com.example.planme.data.models.MemberGroup;
import com.example.planme.utils.DateFormatHelper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MemberGroupRepository {
    DatabaseReference memberDbContext;
    public MemberGroupRepository(DatabaseReference dbContext){
        this.memberDbContext = dbContext.child(FireBaseModel.member);
    }

    public void listenOnAddMember(String userId, final BiConsumer<MemberGroup, Exception> onFinish){
        Query query = this.memberDbContext
                .orderByChild("member").equalTo(userId);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MemberGroup lastMember = snapshot.getValue(MemberGroup.class);
                if(lastMember != null){
                    onFinish.accept(lastMember, null);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MemberGroup lastMember = snapshot.getValue(MemberGroup.class);
                if(lastMember != null){
                    onFinish.accept(lastMember, null);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                MemberGroup lastMember = snapshot.getValue(MemberGroup.class);
                if(lastMember != null){
                    onFinish.accept(lastMember, null);
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MemberGroup lastMember = snapshot.getValue(MemberGroup.class);
                if(lastMember != null){
                    onFinish.accept(lastMember, null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onFinish.accept(null, error.toException());
            }
        });
    }

    public void addMemberToGroup(String groupId, MemberGroup member, final Consumer<Exception> onFinish){
        try{
            String key = memberDbContext.push().getKey();
            member.setId(key);
            member.setDate(DateFormatHelper.getCurrentDateTime());
            member.setGroupId(groupId);

            memberDbContext
                    .child(key).setValue(member)
                    .addOnCompleteListener( task -> {
                        if(task.isSuccessful()){
                            onFinish.accept(null);
                        } else {
                            onFinish.accept(task.getException());
                        }
                    });
        } catch (Exception e){
            onFinish.accept(e);
        }
    }

}
