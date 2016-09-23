package com.javarush.test.level34.lesson15.big01.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class GameObjects {
    Set<Wall> walls;
    Set<Box> boxes;
    Set<Home> homes;
    Player player;
    int widthCell;
    int heightCell;
    int level;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player, int widthCell, int heightCell,int level) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
        this.widthCell = widthCell;
        this.heightCell = heightCell;
        this.level = level;

    }
    public Set<GameObject> getAll() {
        Set<GameObject> set = new HashSet<>();
        set.addAll(walls);
        set.addAll(boxes);
        set.addAll(homes);
        set.add(player);
        return set;
    }

    public int getWidthCell() {
        return widthCell;
    }

    public int getHeightCell() {
        return heightCell;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public Player getPlayer() {
        return player;
    }
}
