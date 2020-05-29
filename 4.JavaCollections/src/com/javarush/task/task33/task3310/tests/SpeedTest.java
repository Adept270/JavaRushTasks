package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class SpeedTest {
    //Должен возвращать время в миллисекундах необходимое для получения идентификаторов для всех строк из strings.
    // Идентификаторы должны быть записаны в ids.
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Long startDate1 = new Date().getTime();
        for (String s: strings){
            ids.add(shortener.getId(s));
        }
        Long endDate1 = new Date().getTime();
        return (endDate1 - startDate1);
    }
    //Должен возвращать время в миллисекундах необходимое для получения строк для всех идентификаторов из ids.
    // Строки должны быть записаны в strings.
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Long startDate1 = new Date().getTime();
        for (Long s : ids){
            strings.add(shortener.getString(s));
        }
        Long endDate1 = new Date().getTime();
        return (endDate1 - startDate1);
    }
    @Test
    public void testHashMapStorage() {
        Shortener mapShourt = new Shortener(new HashMapStorageStrategy());
        Shortener biMapShourt = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> origIds = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        //время, полученное в предыдущем пункте для mapShourt больше, чем для biMapShourt.
        Long mapIds = getTimeToGetIds(mapShourt, origStrings, origIds);
        Long biMapIds = getTimeToGetIds(biMapShourt, origStrings, origIds);
        Assert.assertTrue(mapIds > biMapIds);
        //время, полученное в предыдущем пункте для mapShourt примерно равно времени для biMapShourt.
        Long mapStr = getTimeToGetStrings(mapShourt, origIds, origStrings);
        Long biMapStr = getTimeToGetStrings(biMapShourt, origIds, origStrings);
        Assert.assertEquals(mapStr, biMapStr, 30);
    }
}
