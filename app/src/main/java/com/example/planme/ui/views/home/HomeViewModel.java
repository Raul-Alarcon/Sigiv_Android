package com.example.planme.ui.views.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.data.models.Group;
import com.example.planme.ui.models.GroupUI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
   private final MutableLiveData<List<GroupUI>> groups;
   //GroupRepository groupRepository;

    public HomeViewModel() {
        //this.groupRepository = new GroupRepository();
        this.groups = new MutableLiveData<>();
        this.initialize();

    }
    private void initialize() {

        DatabaseReference myRef = db.getReference();

        myRef.child("groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Group> _groups = new ArrayList<>();
                for (DataSnapshot groupSnapShot: snapshot.getChildren()) {
                     Group group = groupSnapShot.getValue(Group.class);
                     _groups.add(group);
                }

                //groups.setValue(_groups);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void addNewGroup(GroupUI groupUI){
        Group newGroup = new Group();
        newGroup.setName(groupUI.getName());
        newGroup.setDescription(groupUI.getDescription());

        DatabaseReference myRef = db.getReference();
        String key = myRef.child("groups").push().getKey();
        newGroup.setId(key);

        myRef.child("groups")
                .child(key)
                .setValue(newGroup);
    }

    public LiveData<List<GroupUI>> getGroups() { return this.groups; }
}