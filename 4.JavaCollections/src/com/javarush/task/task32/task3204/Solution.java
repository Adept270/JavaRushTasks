package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/* 
Генератор паролей

Реализуй логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов.
2) только цифры и латинские буквы разного регистра.
3) обязательно должны присутствовать цифры, и буквы разного регистра.
Все сгенерированные пароли должны быть уникальные.

Пример правильного пароля:
wMh7smNu


Требования:
1. Класс Solution должен содержать метод getPassword(), который возвращает ByteArrayOutputStream со сгенерированным паролем.
2. Длина пароля должна составлять 8 символов.
3. Пароль должен содержать хотя бы одну цифру.
4. Пароль должен содержать хотя бы одну латинскую букву нижнего регистра.
5. Пароль должен содержать хотя бы одну латинскую букву верхнего регистра.
6. Пароль не должен содержать других символов, кроме цифр и латинских букв разного регистра.
7. Сгенерированные пароли должны быть уникальными.
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StringBuffer sb = new StringBuffer();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        for (int i = 0; i < 4; i++) {
            sb.append(letters.charAt((int) (Math.random() * 26)));
        }
        for (int i = 0; i < 2; i++) {
            sb.append(numbers.charAt((int) (Math.random() * 10)));
            sb.append(Character.toString(letters.charAt((int) (Math.random() * 26))).toUpperCase());
        }

        try {
            byteArrayOutputStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            return byteArrayOutputStream;
        }


        return byteArrayOutputStream;
    }
}