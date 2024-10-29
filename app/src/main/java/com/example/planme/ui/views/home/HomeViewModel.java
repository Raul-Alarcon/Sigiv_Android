package com.example.planme.ui.views.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.data.local.LocalContext;
import com.example.planme.data.models.Group;
import com.example.planme.data.repository.GroupRepository;
import com.example.planme.utils.GenerateID;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

   private final MutableLiveData<ArrayList<Group>> groups;
   //GroupRepository groupRepository;

    public HomeViewModel() {
        //this.groupRepository = new GroupRepository();
        this.groups = new MutableLiveData<>();
        this.initialize();

    }
    private void initialize() {
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            groups.add(new Group(GenerateID.invoke(), "Group " + i, "Description"));
        }
        this.groups.setValue(groups);
    }

    public LiveData<ArrayList<Group>> getGroups() { return this.groups; }
}