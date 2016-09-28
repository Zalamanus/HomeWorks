package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.Map;

/**
 * Created by MVTitov on 18.08.2016.
 */
public class DirectorTablet {
    public void printAdvertisementProfit() {
        float totalAmount = 0;
        for (Map.Entry<String, Long> entry : StatisticEventManager.getInstance().getProfitByDay().entrySet()) {
            float amount = entry.getValue() / 100f;
            totalAmount += amount;
            ConsoleHelper.writeMessage(String.format("%s - %.2f",entry.getKey(),amount));
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f",totalAmount));
    }

    public void printCookWorkloading() {
        for (Map.Entry<String, Map<String,Integer>> entry : StatisticEventManager.getInstance().getCookTimeByDay().entrySet()  ) {
            ConsoleHelper.writeMessage(entry.getKey());
            for (Map.Entry<String,Integer> entry1 : entry.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format("%s - %d min",entry1.getKey(),entry1.getValue()/60));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        for (Advertisement ad : StatisticAdvertisementManager.getInstance().getAdList(true)) {
            ConsoleHelper.writeMessage(String.format("%s - %d",ad.getName(),ad.getHits()));
        }
    }

    public void printArchivedVideoSet() {
        for (Advertisement ad : StatisticAdvertisementManager.getInstance().getAdList(false)) {
            ConsoleHelper.writeMessage(ad.getName());
        }
    }
}
