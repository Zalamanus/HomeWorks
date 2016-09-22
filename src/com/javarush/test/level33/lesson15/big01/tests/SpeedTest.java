package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by MVTitov on 15.09.2016.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        return new Date().getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        return new Date().getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        HashMapStorageStrategy hmss = new HashMapStorageStrategy();
        Shortener shortener1 = new Shortener(hmss);

        HashBiMapStorageStrategy hbmss = new HashBiMapStorageStrategy();
        Shortener shortener2 = new Shortener(hbmss);

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();
        long time1 = getTimeForGettingIds(shortener1, origStrings, ids);
        long time2 = getTimeForGettingIds(shortener2, origStrings, ids);
        Assert.assertTrue(time1 > time2);

        time1 = getTimeForGettingStrings(shortener1, ids, origStrings);
        time2 = getTimeForGettingStrings(shortener2, ids, origStrings);
        Assert.assertEquals(time1,time2,50);

    }
}
