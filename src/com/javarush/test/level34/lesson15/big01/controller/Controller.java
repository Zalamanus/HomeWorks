package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.view.View;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Controller implements EventListener {
    private View view;
    private Model model;

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(int level) {

    }
}
