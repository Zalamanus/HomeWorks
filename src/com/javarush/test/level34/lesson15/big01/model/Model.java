package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Model {
    public static final int FIELD_SELL_SIZE = 30;
    private EventListener eventListener;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\Users\\MVTitov\\Documents\\JavaRushHomeWork\\src\\com\\javarush\\test\\level34\\lesson15\\big01\\res\\levels.txt"));
    private int currentLevel = 1;
    private GameObjects gameObjects = levelLoader.getLevel(currentLevel);

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        currentLevel = level;
        gameObjects = levelLoader.getLevel(level);
        eventListener.setNewSize((gameObjects.getWidthCell())*FIELD_SELL_SIZE+16,(gameObjects.getHeightCell())*FIELD_SELL_SIZE+38);
        eventListener.setNewTitle("Уровень "+gameObjects.level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollision(direction)) return;
        switch (direction) {
            case UP:
                player.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        for (Box box : gameObjects.getBoxes()) { //проверяем столкнулся ли игрок с ящиком
            if (gameObjects.player.isCollision(box, direction)) { //игрок столкнулся с ящиком
                if (checkWallCollision(box, direction)) return true; //этот ящик столкнулся со стеной
                for (Box box1 : gameObjects.getBoxes()) { //проверяем столкнется ли этот ящик с любым другим ящиком1
                    if (box.isCollision(box1, direction)) return true; //этот ящик столкнулся с другим ящиком1
                }
                switch (direction) { //этот ящик не столкнулся со стеной и другим ящиком, перемещаем его
                    case UP:
                        box.move(0, -FIELD_SELL_SIZE);
                        break;
                    case DOWN:
                        box.move(0, FIELD_SELL_SIZE);
                        break;
                    case LEFT:
                        box.move(-FIELD_SELL_SIZE, 0);
                        break;
                    case RIGHT:
                        box.move(FIELD_SELL_SIZE, 0);
                        break;
                }
            }

        }
        return false;
    }

    public void checkCompletion() {
        // проверяем все ли дома заняты ящиками
        boolean allBusy = true; // предполагаем что все занято
        for (Home home : gameObjects.getHomes()) { // ищем для дома ящик
            boolean isBusy = false;
            for (Box box : gameObjects.getBoxes()) {
                if (home.x == box.x && home.y == box.y) {
                    isBusy = true; //ящик для дома есть
                    break; // переходим к следующему дому
                }
            }
            if (!isBusy) allBusy = false; // ящика для дома нет, значит не все занято.
        }
        if (allBusy) // все дома заняты, завершаем уровень.
            eventListener.levelCompleted(currentLevel);
    }

}
