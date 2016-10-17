package com.javarush.test.fifteen.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class GameObjects {
    List<Wall> walls;
    List<Box> boxes;
    NullBox nullBox;
    int widthCell;
    int heightCell;
    int level;

    public GameObjects(List<Wall> walls, List<Box> boxes, NullBox nullBox, int widthCell, int heightCell, int level) {
        this.walls = walls;
        this.boxes = boxes;
        this.nullBox = nullBox;
        this.widthCell = widthCell;
        this.heightCell = heightCell;
        this.level = level;

    }
    public Set<GameObject> getAll() {
        Set<GameObject> set = new HashSet<>();
        set.addAll(walls);
        set.addAll(boxes);
        return set;
    }

    public int getWidthCell() {
        return widthCell;
    }

    public int getHeightCell() {
        return heightCell;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public NullBox getNullBox() {
        return nullBox;
    }
}
