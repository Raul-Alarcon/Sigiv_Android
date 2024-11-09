package com.example.planme.data.repository;

import com.example.planme.data.local.LocalContext;
import com.example.planme.data.models.Entity;
import com.example.planme.data.models.Group;

import java.util.ArrayList;

public class GroupRepository extends  BaseRepository<Group> {
    public GroupRepository() {
        super(LocalContext.context);
    }

    @Override
    public ArrayList<Group> getAll() {
        return super.filterByClass(Group.class);
    }

    @Override
    public Group getById(String id) {
        return super.filterByClass(Group.class).stream()
                .filter( g -> g.getId().equals(id))
                .findFirst().orElse(null);
    }
}
