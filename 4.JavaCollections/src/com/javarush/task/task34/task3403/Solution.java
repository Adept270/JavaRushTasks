package com.javarush.task.task34.task3403;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Разложение на множители с помощью рекурсии
*/
public class Solution {

    public void recursion(int n) {
       int i = 2;

       while (i <= n) {
           if (n % i == 0) {
               if (n != i) {
                   System.out.println(n + " ");
                   recursion(n / i);
               }
               else {
                   System.out.println(n);
               }
               return;
           }
           i++;
       }
    }
}
