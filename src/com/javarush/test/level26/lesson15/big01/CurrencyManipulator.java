package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by MVTitov on 12.08.2016.
 */
public class CurrencyManipulator {
    private String currencyCode;
    public Map<Integer, Integer> denominations = new TreeMap<>(Collections.<Integer>reverseOrder());

    private CurrencyManipulator() {
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (!denominations.containsKey(denomination))
            denominations.put(denomination, count);
        else
            denominations.put(denomination, denominations.get(denomination) + count);

    }

    public int getTotalAmount() {
        int summ = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            summ += entry.getKey() * entry.getValue();
        }
        return summ;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }
    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer,Integer> resultmap = new TreeMap<>(Collections.<Integer>reverseOrder());
        Map<Integer,Integer> reservemap = new TreeMap<>(Collections.<Integer>reverseOrder());
        reservemap.putAll(denominations);
        int needSum = expectedAmount;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int i = needSum / entry.getKey();
            if (i > entry.getValue()) i = entry.getValue();
            if (i <= 0 ) continue;
            resultmap.put(entry.getKey(),i);
            denominations.put(entry.getKey(),entry.getValue()-i);
            needSum -= entry.getKey()*i;
            if (needSum == 0) break;
        }
        if (needSum > 0) {
            denominations = reservemap;
            throw new NotEnoughMoneyException();
        }
        return resultmap;
    }
}
