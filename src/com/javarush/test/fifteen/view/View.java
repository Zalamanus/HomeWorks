package com.javarush.test.fifteen.view;

import com.javarush.test.fifteen.controller.Controller;
import com.javarush.test.fifteen.controller.EventListener;
import com.javarush.test.fifteen.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public Field getField() {
        return field;
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Пятнашки");
        setVisible(true);
    }


    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }
    public void update() {
        field.repaint();
    }
    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }
    public void completed() {
        update();
        JOptionPane.showMessageDialog(this, "Уровень пройден!");
        controller.restart();
    }

}
