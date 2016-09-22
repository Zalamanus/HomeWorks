package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;
import com.javarush.test.level33.lesson15.big01.tests.FunctionalTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by MVTitov on 13.09.2016.
 */
public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String string : strings) {
            set.add(shortener.getId(string));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long key : keys) {
            set.add(shortener.getString(key));
        }
        return set;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        Date start = new Date();
        Set<Long> ids = getIds(shortener, testSet);
        Date finish = new Date();
        Helper.printMessage(finish.getTime()-start.getTime()+" - добавление в базу");
        start = new Date();
        Set<String> finalSet = getStrings(shortener,ids);
        finish = new Date();
        Helper.printMessage(finish.getTime()-start.getTime()+" - извлечение из базы");
        if (testSet.equals(finalSet)) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }

    public static void main(String[] args) {
        StorageStrategy strategy = new HashBiMapStorageStrategy();
        testStrategy(strategy,10000);
    }
}
