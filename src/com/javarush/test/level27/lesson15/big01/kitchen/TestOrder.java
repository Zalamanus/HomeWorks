package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;

/**
 * Created by MVTitov on 21.08.2016.
 */
public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }
    protected void initDishes() {
        Dish[] allDishes = Dish.values();
        for (int i = 0; i < 5; i++) {
            dishes.add(allDishes[(int) (Math.random()*allDishes.length)]);
        }
    }
}
