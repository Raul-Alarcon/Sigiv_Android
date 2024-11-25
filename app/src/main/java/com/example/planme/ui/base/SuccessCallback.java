package com.example.planme.ui.base;

import com.example.planme.data.models.Group;
import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface SuccessCallback {
    void onSuccess(DataSnapshot snapshot);
}
