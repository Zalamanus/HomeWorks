package com.javarush.test.level34.lesson15.big01.view;


import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.*;
import com.javarush.test.level34.lesson15.big01.model.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
        //new Box(20,20).draw(g);
        //new Player(40,40).draw(g);
        //new Home(60,60).draw(g);
        //new Wall(80,80).draw(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());
        for (GameObject gameObject : view.getGameObjects().getAll()) {
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
