package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.Controller;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;

import javax.swing.*;
import java.awt.*;

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
        setTitle("Сокобан.");
        setVisible(true);
    }

    public void setNewTitle(String s) {
        setTitle("Сокобан. "+s);
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
    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(this, "Уровень " + level + " пройден!");
        controller.startNextLevel();
    }

    public int selectLevel() {
        int level = -1;
        try {
            level = Integer.parseInt(JOptionPane.showInputDialog("Введите номер уровня:"));
        } catch (Exception e) {
        }
        return level;
    }
}
