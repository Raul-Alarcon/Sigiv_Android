package com.example.planme.utils;

import java.util.Random;

public class GenerateCode {
    public static String generateUniqueCode() {
        long currentTime = System.currentTimeMillis();

        String timePart = Long.toHexString(currentTime).toUpperCase();
        String randomPart = generateRandomString(3);

        String code = timePart + randomPart;

        if (code.length() > 8) {
            code = code.substring(0, 8);
        }

        return code;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
