package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов

1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя существующего файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его (используй метод FileUtils.deleteFile).
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
2.2.2. Переименовать resultFileAbsolutePath в 'allFilesContent.txt' (используй метод FileUtils.renameFile, и, если понадобится, FileUtils.isExist).
2.2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
В качестве разделителя пути используй "/".


Требования:
1. Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
2. Нужно создать поток для записи в переименованный файл.
3. Пройдись по всем файлам в директории, которая приходит первым параметром в main, и всех ее поддиректориях. Файлы с размером более 50 байт нужно удалить используя метод FileUtils.deleteFile.
4. Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt в отсортированном по имени файла порядке.
5. Поток для записи в файл нужно закрыть.
*/
//public class Solution {
//    public static void main(String[] args) {
//
//        Set<String> set = new TreeSet<>();
//
//        String path = args[0];  //"C:/Users/SBT-Ryabtsev-AV/Downloads/test/file";
//        File startPath = new File(path);
//
//        String resultFileAbsolutePath = args[1]; //"C:/Users/SBT-Ryabtsev-AV/Downloads/test/file/file.txt";
//        File fileToProcess = new File(resultFileAbsolutePath);
//        File fileForRename = new File(fileToProcess.getParent() + "/allFilesContent.txt");
//
//        FileWriter writer = null;
//
//        if (FileUtils.isExist(fileToProcess)) {
//            FileUtils.renameFile(fileToProcess, fileForRename);
//        }
//
//        try {
//
//            writer = new FileWriter(fileForRename);
//
//            processing(startPath, set);
//
//            for (String s : set) {
//                writer.write(s + "\n");
//            }
//
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void processing(File startFile, Set<String> set) {
//
//        File[] fileList = startFile.listFiles();
//
//        if (fileList != null) {
//            for (File f : fileList) {
//                if (f.isDirectory()) {
//                    processing(f, set);
//                    continue;
//                }
//
//                if (f.length() > 50) {
//                  //  System.out.println("length > 50 " + f.getName());
//                    FileUtils.deleteFile(f);
//                } else {
//                    set.add(f.getName());
//                }
//            }
//        }
//
//    }
//
//    private static void print() {
//        File[] fileList = new File("C:/Users/SBT-Ryabtsev-AV/Downloads/test/file")
//                .listFiles();
//
//        Arrays.stream(fileList).forEach((p) -> System.out.println(p));
//        System.out.println("-----------------------------------------");
//
//    }
//}

public class Solution {
    private static ArrayList<File> fileList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        try (FileOutputStream fileOutputStream = new FileOutputStream(allFilesContent)) {

            fillFileList(path.getPath());
            fileList.sort(new FileNameComparator());

            for (File file : fileList) {
                FileInputStream fileInputStream = new FileInputStream(file);
                while (fileInputStream.available() > 0) {
                    fileOutputStream.write(fileInputStream.read());
                }
                fileOutputStream.write(System.lineSeparator().getBytes());
                fileOutputStream.flush();

                fileInputStream.close();
            }
        }
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }


    //Рекурсивно пробегаем поддиректории и заполняем список файлов
    private static void fillFileList(String path) {
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                fillFileList(file.getAbsolutePath());
                continue;
            }
            if (file.length() > 50)
                FileUtils.deleteFile(file);
            else
                fileList.add(file);
        }
    }
}

//Компаратор для сравнения
class FileNameComparator implements Comparator<File> {
    public int compare(File first, File second) {
        return first.getName().compareTo(second.getName());
    }
}
