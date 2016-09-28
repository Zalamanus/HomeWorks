package com.javarush.test.level27.lesson15.big01.kitchen;

/**
 * Created by MVTitov on 16.08.2016.
 */
public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);
    private int duration;

    Dish(int duration) {
        this.duration=duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < values().length; i++) {
            s.append(values()[i].name());
            if (i != values().length-1) s.append(", ");
        }
        return s.toString();
    }
}
