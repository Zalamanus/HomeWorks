package com.javarush.test.level27.lesson15.big01;


import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MVTitov on 16.08.2016.
 */
public class Tablet {
    private final int number;
    private AdvertisementManager manager;
    public static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;


    public Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void createTestOrder() {
        Order order = null;
        try {
            order = new TestOrder(this);
            method(order);
        } catch (RuntimeException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void method(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        if (!order.isEmpty()) {
            queue.add(order);
            manager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            manager.processVideos();
        }
    }

    public void createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            method(order);
        } catch (RuntimeException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }


    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
