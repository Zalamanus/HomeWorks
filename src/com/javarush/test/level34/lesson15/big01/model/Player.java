package com.javarush.test.level34.lesson15.big01.model;



import java.awt.*;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(x-width/2,y-height/2,width,height);
    }

    @Override
    public void draw(Graphics graphics, Color color) {
        graphics.setColor(color);
        graphics.fillOval(x-width/2,y-height/2,width,height);
    }

    @Override
    public void move(int x, int y) {
        setX(this.x + x);
        setY(this.y + y);
    }
}
