package com.javarush.test.fifteen.model;

import java.awt.*;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Box extends CollisionObject implements Movable {
    private int number;
    private char[] num2print;
    public Box(int x, int y, int number) {
        super(x, y);
        this.number = number;
        num2print = String.valueOf(number).toCharArray();
    }

    @Override
    public void draw(Graphics graphics) {
            graphics.setColor(Color.YELLOW);
            graphics.drawRect(x + 1 - width / 2, y + 1 - height / 2, width - 2, height - 2);
            graphics.drawChars(num2print, 0, num2print.length, x - width / 5, y);
    }

    @Override
    public void draw(Graphics graphics, Color color) {

    }


    @Override
    public void move(int x, int y) {
        setX(this.x+x);
        setY(this.y+y);
    }

    @Override
    public void set(int x, int y) {
        setX(x);
        setY(y);
    }


    public int getNumber() {
        return number;
    }

}
