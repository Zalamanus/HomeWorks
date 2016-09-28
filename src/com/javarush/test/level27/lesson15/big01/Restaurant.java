package com.javarush.test.level27.lesson15.big01;


import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by MVTitov on 16.08.2016.
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> NEWQUEUE = new LinkedBlockingQueue<>();


    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        List<Tablet> tablets = new ArrayList<>();
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Elly");
        cook1.setQueue(NEWQUEUE);
        cook2.setQueue(NEWQUEUE);
        Thread cook1Thread = new Thread(cook1);
        Thread cook2Thread = new Thread(cook2);
        Waitor waitor = new Waitor();
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);
        for (int i = 1; i <= 5; i++) {
            tablets.add(new Tablet(i));
            tablets.get(i-1).setQueue(NEWQUEUE);
        }
        RandomOrderGeneratorTask generator = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(generator);
        thread.start();
        cook1Thread.start();
        cook2Thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
        cook1Thread.interrupt();
        cook2Thread.interrupt();
        try {
            thread.join();
            cook1Thread.join();
            cook2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        DirectorTablet dirTablet = new DirectorTablet();
        dirTablet.printAdvertisementProfit();
        dirTablet.printCookWorkloading();
        dirTablet.printActiveVideoSet();
        dirTablet.printArchivedVideoSet();
    }
}
