package com.example.planme.data.local;

import com.example.planme.data.models.Entity;
import com.example.planme.data.models.Group;
import com.example.planme.utils.GenerateID;

import java.util.ArrayList;

public class LocalContext {
    public final static ArrayList<Entity> context = new ArrayList<>();

    public static void setUpContext(){
        for(int i = 0; i < 10; i++){
            context.add(new Group(GenerateID.invoke(), "Group " + i, "Esta es una description de prueba"));
        }
    }


}
