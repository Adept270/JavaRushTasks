package com.javarush.task.task32.task3210;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile

В метод main приходят три параметра:
1) fileName - путь к файлу;
2) number - число, позиция в файле;
3) text - текст.

Считать текст с файла начиная с позиции number, длинной такой же как и длинна переданного текста в третьем параметре.
Если считанный текст такой же как и text, то записать в конец файла строку 'true', иначе записать 'false'.
Используй RandomAccessFile и его методы seek(long pos), read(byte b[], int off, int len), write(byte b[]).
Используй один из конструкторов класса String для конвертации считанной строчки в текст.
*/

public class Solution {
    public static void main(String... args) throws IOException {
        File file = new File(args[0]); //"C:\\Users\\SBT-Ryabtsev-AV\\Desktop\\test.txt");
        long number = Long.parseLong(args[1]); // 13;
        String text = args[2]; // "file";
        RandomAccessFile raf = new RandomAccessFile(file.toString(), "rw");
        byte[] buffer = new byte[text.length()];
        raf.seek(number);
        raf.read(buffer, 0, text.length());
        String fondText = new String(buffer);

        String resultEquals = text.equals(fondText) ? "true" : "false";
        raf.seek(raf.length());
        raf.write(resultEquals.getBytes());

        raf.close();

    }
}
