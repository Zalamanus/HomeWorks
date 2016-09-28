package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by MVTitov on 17.08.2016.
 */
public class Waitor implements Observer {
    @Override
    public void update(Observable observable, Object o) {
        ConsoleHelper.writeMessage(o + " was cooked by " + observable);
    }
}
