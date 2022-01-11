package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.Arrays;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("adsf"));

    }

    public static boolean isPalindromePermutation(String s) {

        boolean foundOdd = false;
        int[] tableCount = new int[256];

        for (char c : s.toLowerCase().toCharArray()) {
            tableCount[c] += 1;
        }

        for (int count : tableCount) {
            if (count % 2 != 0) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }

        return true;
    }
//
//        char[] charsIn = s.toLowerCase(Locale.ROOT).toCharArray();
//        char[] charsOut = new char[charsIn.length];
//
//        for (int i = 0; i < charsIn.length; i++) {
//            charsOut[charsIn.length - 1 - i] = charsIn[i];
//        }
//
//        return (Arrays.compare(charsIn, charsOut) == 0);


}
