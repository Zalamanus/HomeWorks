package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by MVTitov on 24.08.2016.
 */
public class Soldier extends Human implements Alive {

    public Soldier(String name, int age) {
        super(name, age);
    }

    public void live() {
            fight();
    }

    public void fight() {
    }
}
