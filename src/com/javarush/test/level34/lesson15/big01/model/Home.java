package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        int size = Model.FIELD_SELL_SIZE;
        graphics.setColor(Color.RED);
        graphics.drawOval(x+size/2-width/2, y+size/2-height/2, width, height);
    }
}
