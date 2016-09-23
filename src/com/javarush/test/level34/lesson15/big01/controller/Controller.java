package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.view.Field;
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

    public void setNewTitle(String s) {
        view.setNewTitle(s);
    }

    @Override
    public void setNewSize(int width, int height) {
        view.setSize(width,height);
    }

    @Override
    public void selectLevel() {
        int level = view.selectLevel();
        if (level != -1) model.restartLevel(level);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
