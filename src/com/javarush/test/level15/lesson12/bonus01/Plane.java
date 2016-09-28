package com.javarush.test.level15.lesson12.bonus01;


/**
 * Created by MVTitov on 05.07.2016.
 */
public class Plane implements Flyable {
    private int passenger;
    @Override
    public void fly() {

    }

    Plane(int passenger) {
        this.passenger = passenger;
    }
}
