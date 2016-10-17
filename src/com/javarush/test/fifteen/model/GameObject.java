package com.javarush.test.fifteen.model;

import java.awt.*;

/**
 * Created by MVTitov on 22.09.2016.
 */
public abstract class GameObject {
    int x;
    int y;
    int width;
    int height;

    public abstract void draw(Graphics graphics);

    public abstract void draw(Graphics graphics, Color color);

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        width = Model.FIELD_SELL_SIZE;
        height = Model.FIELD_SELL_SIZE;
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
