package com.example.planme.utils;

import java.util.UUID;

public class GenerateID {
    public static String invoke(){
        return UUID.randomUUID().toString();
    }
}
