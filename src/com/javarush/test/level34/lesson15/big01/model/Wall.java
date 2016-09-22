package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x-width/2,y-height/2,width,height);
    }
}
