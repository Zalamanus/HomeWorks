package com.javarush.test.fifteen.model;

import java.awt.*;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class NullBox extends Box {
    public NullBox(int x, int y) {
        super(x, y, 16);
    }

    @Override
    public void draw(Graphics graphics) {
            //graphics.setColor(Color.BLUE);
            //graphics.drawRect(x + 1 - width / 2, y + 1 - height / 2, width - 2, height - 2);
    }




}
