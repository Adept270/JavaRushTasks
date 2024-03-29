package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
        public void testStorage(Shortener shortener) {
        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = String.copyValueOf(s1.toCharArray());

        Long id1 = shortener.getId(s1);
        Long id2 = shortener.getId(s2);
        Long id3 = shortener.getId(s3);

        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        String value1 = shortener.getString(id1);
        String value2 = shortener.getString(id2);
        String value3 = shortener.getString(id3);

        Assert.assertEquals(s1, value1);
        Assert.assertEquals(s2, value2);
        Assert.assertEquals(s3, value3);
    }

        @Test
        public void testHashMapStorageStrategy() {
                HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
                Shortener shortener = new Shortener(hashMapStorageStrategy);
                testStorage(shortener);
        }

        @Test
        public void testOurHashMapStorageStrategy() {
                OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
                Shortener shortener = new Shortener(ourHashMapStorageStrategy);
                testStorage(shortener);
        }

        @Test
        public void testFileStorageStrategy() {
                FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
                Shortener shortener = new Shortener(fileStorageStrategy);
                testStorage(shortener);
        }

        @Test
        public void testHashBiMapStorageStrategy() {
                HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
                Shortener shortener = new Shortener(hashBiMapStorageStrategy);
                testStorage(shortener);
        }

        @Test
        public void testDualHashBidiMapStorageStrategy() {
                DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
                Shortener shortener = new Shortener(dualHashBidiMapStorageStrategy);
                testStorage(shortener);
        }

        @Test
        public void testOurHashBiMapStorageStrategy() {
                OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
                Shortener shortener = new Shortener(ourHashBiMapStorageStrategy);
                testStorage(shortener);
        }

        @Test
        public void testDataBaseStorageStrategy() {
            DataBaseStorageStrategy dataBaseStorageStrategy = new DataBaseStorageStrategy();
            Shortener shortener = new Shortener(dataBaseStorageStrategy);
            testStorage(shortener);
        }
}
