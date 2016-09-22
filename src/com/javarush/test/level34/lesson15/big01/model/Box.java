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
        graphics.setColor(Color.YELLOW);
        graphics.drawRect(x-width/2,y-height/2,width,height);
        graphics.drawLine(x-width/2,y-height/2,x+width/2,y+height/2);
        graphics.drawLine(x-width/2,y+height/2,x+width/2,y-height/2);
    }

    @Override
    public void move(int x, int y) {
        setX(this.x+x);
        setY(this.y+y);
    }
}
