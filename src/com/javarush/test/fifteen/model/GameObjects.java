package com.javarush.test.fifteen.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class GameObjects {
    List<Box> boxes;
    NullBox nullBox;
    int widthCell;
    int heightCell;

    public GameObjects(List<Box> boxes, NullBox nullBox, int widthCell, int heightCell) {
        this.boxes = boxes;
        this.nullBox = nullBox;
        this.widthCell = widthCell;
        this.heightCell = heightCell;

    }
    public Set<GameObject> getAll() {
        Set<GameObject> set = new HashSet<>();
        set.addAll(boxes);
        set.add(nullBox);
        return set;
    }

    public int getWidthCell() {
        return widthCell;
    }

    public int getHeightCell() {
        return heightCell;
    }


    public List<Box> getBoxes() {
        return boxes;
    }

    public NullBox getNullBox() {
        return nullBox;
    }
}
