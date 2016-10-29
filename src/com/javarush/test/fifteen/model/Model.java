package com.javarush.test.fifteen.model;

import com.javarush.test.fifteen.controller.EventListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class Model {
    public static final int FIELD_SELL_SIZE = 50;
    private EventListener eventListener;
    private LevelLoader levelLoader = new LevelLoader();
    private GameObjects gameObjects = levelLoader.getLevel();

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel() {
        gameObjects = levelLoader.getLevel();
        eventListener.setNewSize((gameObjects.getWidthCell())*FIELD_SELL_SIZE+9,(gameObjects.getHeightCell())*FIELD_SELL_SIZE+28);
    }

    public void restart() {
        restartLevel();
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
                    Collections.swap(boxes, boxes.indexOf(box), boxes.indexOf(nullBox));
                    break;
                case DOWN:
                    box.move(0, FIELD_SELL_SIZE);
                    nullBox.move(0, -FIELD_SELL_SIZE);
                    Collections.swap(boxes, boxes.indexOf(box), boxes.indexOf(nullBox));
                    break;
                case LEFT:
                    box.move(-FIELD_SELL_SIZE, 0);
                    nullBox.move(FIELD_SELL_SIZE,0);
                    Collections.swap(boxes, boxes.indexOf(box), boxes.indexOf(nullBox));
                    break;
                case RIGHT:
                    box.move(FIELD_SELL_SIZE, 0);
                    nullBox.move(-FIELD_SELL_SIZE,0);
                    Collections.swap(boxes, boxes.indexOf(box), boxes.indexOf(nullBox));
                    break;
            }
            break;

        }

        checkCompletion();
    }



    public boolean checkCollision(CollisionObject gameObject, Direction direction) {
        if (gameObject.isOutOfField(direction)) return true;
        for (Box box : gameObjects.getBoxes()) {
            if (box instanceof NullBox) continue;
            if (gameObject.isCollision(box,direction)) {
                return true;
            }
        }
        return false;
    }

    public void checkCompletion() {
        List<Box> boxList = gameObjects.getBoxes();
        for (Box box : boxList) {
            if (boxList.indexOf(box)+1 != box.getNumber()) return;
        }
        eventListener.levelCompleted();


    }

}
