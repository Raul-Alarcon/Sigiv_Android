package com.example.planme.ui.views.me;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.ui.models.UserUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MeViewModel extends ViewModel {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userSession;

    MutableLiveData<UserUI> userUI = new MutableLiveData<>();

    public MeViewModel(){
        userSession = auth.getCurrentUser();
        this.initialize();
    }

    public void initialize(){
        if(this.userSession != null){

            UserUI user = new UserUI(
                    userSession.getUid(),
                    userSession.getPhotoUrl(),
                    userSession.getEmail(),
                    userSession.getDisplayName());

            this.userUI.setValue(user);
        }
    }

    public void signOut(){
        auth.signOut();
    }

    public LiveData<UserUI> getUser(){
        return this.userUI;
    }

}