package com.javarush.test.level26.lesson15.big01;

import java.util.*;

/**
 * Created by MVTitov on 12.08.2016.
 */
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!map.containsKey(currencyCode)) map.put(currencyCode, new CurrencyManipulator(currencyCode));
        return map.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
