package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл

В метод main приходят три параметра:
1) fileName - путь к файлу;
2) number - число, позиция в файле;
3) text - текст.
Записать text в файл fileName начиная с позиции number.
Запись должна производиться поверх рх старых данных, содержащихся в файле.
Если файл слишком короткий, то записать в конец файла.
Используй RandomAccessFile и его методы seek и write.
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0]; //"C:\\Users\\SBT-Ryabtsev-AV\\Desktop\\test.txt";
        long number = Integer.parseInt(args[1]);
        String text = args[2]; //"O-la-la!";
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        long fileLength = randomAccessFile.length();

        if (number > fileLength) {
            randomAccessFile.seek(fileLength);
            randomAccessFile.write(text.getBytes());
        }

        randomAccessFile.seek(number);
        randomAccessFile.write(text.getBytes());
        randomAccessFile.close();

    }
}
