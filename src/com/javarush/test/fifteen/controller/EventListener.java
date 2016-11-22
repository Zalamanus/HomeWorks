package com.javarush.test.fifteen.controller;

import com.javarush.test.fifteen.model.Direction;

/**
 * Created by MVTitov on 22.09.2016.
 */
public interface EventListener {
    void move(Direction direction); //передвинуть объект в определенном направлении.
    void restart(); //начать заново текущий уровень.
    void levelCompleted(); //уровень с номером level завершён.
    void setNewSize(int width, int height);
}
