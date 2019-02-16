package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?

Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.

Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией - выведи "[полный путь] - не папка" и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок - [количество папок в директории]
Всего файлов - [количество файлов в директории и поддиректориях]
Общий размер - [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.

Квадратные скобки [ ] выводить на экран не нужно.


Требования:
1. Метод main должен считывать путь к папке с консоли.
2. Если введенный путь не является директорией - нужно вывести "[полный путь] - не папка" и завершить работу.
3. На консоль должна быть выведена следующая информация: "Всего папок - [количество папок в директории]".
4. На консоль должна быть выведена следующая информация: "Всего файлов - [количество файлов в директории и поддиректориях]".
5. На консоль должна быть выведена следующая информация: "Общий размер - [общее количество байт, которое хранится в директории]".
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path directory = Paths.get(reader.readLine());

        if (Files.isDirectory(directory)) {
            MyFileVisitor fileVisitor = new MyFileVisitor();
            Files.walkFileTree(directory, fileVisitor);

            System.out.println("Всего папок - " + (fileVisitor.getDirCount() - 1));
            System.out.println("Всего файлов - " + fileVisitor.getFilesCount());
            System.out.println("Общий размер - " + fileVisitor.getSize());
        }
        else {
            System.out.println(directory.toAbsolutePath().toString() + " - не папка");
        }



    }
}
