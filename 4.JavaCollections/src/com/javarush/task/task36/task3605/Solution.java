package com.javarush.task.task36.task3605;

import javax.imageio.stream.FileImageInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

/* 
Использование TreeSet

Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортируй буквы по алфавиту и выведи на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то выведи их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрой потоки.

Пример 1 данных входного файла:
zBk yaz b-kN

Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB

Пример 2 вывода:
abc

Подсказка: использовать TreeSet


Требования:
1. Программа должна использовать класс TreeSet.
2. У объекта типа TreeSet вызови метод add для добавления элементов.
3. Программа должна выводить результат на экран.
4. Вывод программы должен соответствовать условию задачи.
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        String fileName = args[0];
        SortedSet<Character> treeSet = new TreeSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        while (reader.ready()) {
            String s = reader.readLine().toLowerCase().replaceAll("[^\\p{Alpha}]", "");
            for (Character c : s.toCharArray()) {
                treeSet.add(c);
            }
        }

        Iterator<Character> iterator = treeSet.iterator();

        int n = treeSet.size() < 5 ? treeSet.size() : 5;

        while (n > 0) {
            System.out.print(iterator.next());
            n--;
        }

        reader.close();
    }
}
