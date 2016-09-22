package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int size = Model.FIELD_SELL_SIZE;
        graphics.setColor(Color.YELLOW);
        graphics.drawRect(x,y,width,height);
        graphics.drawLine(x,y,x+size,y+size);
        graphics.drawLine(x,y+size,x+size,y);
    }

    @Override
    public void move(int x, int y) {
        setX(this.x+x);
        setY(this.y+y);
    }
}
