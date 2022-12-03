package com.mastercard.bankapp.constants;

import org.springframework.stereotype.Component;

import java.util.Random;


public class Helper {
    public static String generateRandomValues(String constants,int length){
        char[] chars = constants.toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);
        return sb.toString();
    }
}
