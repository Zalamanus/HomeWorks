package com.javarush.test.fifteen.model;

import com.javarush.test.fifteen.controller.EventListener;

import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Model {
    public static final int FIELD_SELL_SIZE = 30;
    private EventListener eventListener;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\Users\\MVTitov\\JavaRushHomeWork\\src\\com\\javarush\\test\\fifteen\\res\\levels.txt"));
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

    public void move(Direction direction) {
        List<Box> boxes = gameObjects.getBoxes();
        NullBox nullBox = gameObjects.getNullBox();
        for (Box box : boxes) {
            if (box == nullBox) continue;
            if (checkCollision(box, direction)) continue;
            switch (direction) {
                case UP:
                    box.move(0, -FIELD_SELL_SIZE);
                    nullBox.move(0, FIELD_SELL_SIZE);
                    break;
                case DOWN:
                    box.move(0, FIELD_SELL_SIZE);
                    nullBox.move(0, -FIELD_SELL_SIZE);
                    break;
                case LEFT:
                    box.move(-FIELD_SELL_SIZE, 0);
                    nullBox.move(FIELD_SELL_SIZE,0);
                    break;
                case RIGHT:
                    box.move(FIELD_SELL_SIZE, 0);
                    nullBox.move(-FIELD_SELL_SIZE,0);
                    break;
            }
            break;

        }

        checkCompletion();
    }

    public boolean checkCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        for (Box box : gameObjects.getBoxes()) {
            if (box instanceof NullBox) continue;
            if (gameObject.isCollision(box,direction)) {
                return true;
            }
        }
        return false;
    }

    public void checkCompletion() {
/*        // проверяем все ли дома заняты ящиками
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
            eventListener.levelCompleted(currentLevel);*/
    }

}
