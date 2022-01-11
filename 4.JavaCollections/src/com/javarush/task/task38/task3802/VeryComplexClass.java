package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.*;
import java.util.Scanner;

public class VeryComplexClass {
    public void veryComplexMethod() throws FileNotFoundException {
        //напишите тут ваш код
        Scanner scanner = new Scanner(new File(""));
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();

    }
}
