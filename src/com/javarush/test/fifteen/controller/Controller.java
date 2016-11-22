package com.javarush.test.fifteen.controller;

import com.javarush.test.fifteen.model.Direction;
import com.javarush.test.fifteen.model.GameObjects;
import com.javarush.test.fifteen.model.Model;
import com.javarush.test.fifteen.view.Field;
import com.javarush.test.fifteen.view.View;

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
        model.setEventListener(this);
        view.setEventListener(this);
        model.restart();
    }

    public Field getField() {
        return view.getField();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }


    @Override
    public void setNewSize(int width, int height) {
        view.setSize(width,height);
    }


    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void levelCompleted() {
        view.completed();
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
