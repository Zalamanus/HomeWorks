package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by MVTitov on 03.07.2016.
 */
public class Singleton {
    private static Singleton sin = new Singleton();
    private Singleton() {
    }

    public static Singleton getInstance() {
        return sin;
    }
}
