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
    public void move(int x, int y) {
/*        int step = 5;
        for (int i = 0; i < 5; i++) {
            setX(this.x + x/step);
            setY(this.y + y/step);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        setX(this.x + x);
        setY(this.y + y);
    }
    // todo сделать метод move(direction), чтобы плавно двигал игрока.
}
