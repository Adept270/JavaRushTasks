package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.DataBaseStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.DataFormatException;

public class Solution {
    public static void main(String[] args) {
//        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
//        testStrategy(hashMapStorageStrategy, 10000);

        DataBaseStorageStrategy dataBaseStorageStrategy = new DataBaseStorageStrategy();
        dataBaseStorageStrategy.put(1L, "one");
        System.out.println(dataBaseStorageStrategy.containsValue("one"));
        System.out.println(dataBaseStorageStrategy.containsKey(1L));
        System.out.println(dataBaseStorageStrategy.getKey("one"));
        System.out.println(dataBaseStorageStrategy.getValue(1L));
        dataBaseStorageStrategy.close();

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> keySet = new HashSet<>();

        for (String value : strings) {
            keySet.add(shortener.getId(value));
        }
        return keySet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();

        for (Long key : keys) {
            stringSet.add(shortener.getString(key));
        }
        return stringSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        //Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber
        Set<String> testSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        //Заполнить мапу строками и изменить время работы
        Date startGetIdMethod = new Date();
        Set<Long> ids = getIds(shortener, testSet);
        Date finishGetIfMethod = new Date();
        Long deltaGetId = finishGetIfMethod.getTime() - startGetIdMethod.getTime();
        Helper.printMessage(Long.toString(deltaGetId));
        //<-

        //Замерять и выводить время необходимое для отработки метода getStrings
        // для заданной стратегии и полученного в предыдущем пункте множества идентификаторов.
        Date startGetStringMethod = new Date();
        Set<String> strings = getStrings(shortener, ids);
        Date finishGetStrings = new Date();
        Long deltaGetStrings = finishGetStrings.getTime() - startGetIdMethod.getTime();
        Helper.printMessage(Long.toString(deltaGetStrings));

        //Сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества,
        // которое было возвращено методом getStrings.
        // Если множества одинаковы, то выведи "Тест пройден.", иначе "Тест не пройден."
        if (strings.equals(testSet))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
