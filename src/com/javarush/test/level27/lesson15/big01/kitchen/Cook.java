package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by MVTitov on 16.08.2016.
 */
public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }


    public synchronized void startCookingOrder(Order order) throws InterruptedException {
            busy = true;
            ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + (order.getTotalCookingTime() + "min"));
            Thread.sleep(order.getTotalCookingTime() * 10l);
            StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
            setChanged();
            notifyObservers(order);
            busy = false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!queue.isEmpty() && !isBusy()) {
                    startCookingOrder(queue.take());
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
        }

    }
}
