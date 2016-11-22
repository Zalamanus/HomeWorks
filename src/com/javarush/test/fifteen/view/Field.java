package com.javarush.test.fifteen.view;


import com.javarush.test.fifteen.controller.EventListener;
import com.javarush.test.fifteen.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;


/**
 * Created by MVTitov on 22.09.2016.
 */
public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler(this);
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());
        Set<GameObject> gameObjectsSet = view.getGameObjects().getAll();
        for (GameObject gameObject : gameObjectsSet) {
            gameObject.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public static class KeyHandler extends KeyAdapter {
        Field field;

        public KeyHandler(Field field) {
            this.field = field;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    field.eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    field.eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    field.eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    field.eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    field.eventListener.restart();
                    break;
            }
        }
    }
}
