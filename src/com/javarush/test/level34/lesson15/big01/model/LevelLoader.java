package com.javarush.test.level34.lesson15.big01.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.test.level34.lesson15.big01.model.Model.FIELD_SELL_SIZE;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }
    public GameObjects getLevel(int level) {


        int center = FIELD_SELL_SIZE/2;
        Player player = new Player(FIELD_SELL_SIZE * 0 +center,FIELD_SELL_SIZE * 0 + center);
        Set<Home> homeSet = new HashSet<>();
        homeSet.add(new Home(FIELD_SELL_SIZE * 3 +center,FIELD_SELL_SIZE * 3 + center));
        homeSet.add(new Home(FIELD_SELL_SIZE * 4 +center,FIELD_SELL_SIZE * 3 + center));
        Set<Box> boxSet = new HashSet<>();
        boxSet.add(new Box(FIELD_SELL_SIZE * 1 + center,FIELD_SELL_SIZE * 1 + center));
        boxSet.add(new Box(FIELD_SELL_SIZE * 2 + center,FIELD_SELL_SIZE * 1 + center));
        Set<Wall> wallSet = new HashSet<>();
        for (int i = 2; i < 6; i++) {
            wallSet.add(new Wall(FIELD_SELL_SIZE * i +center,FIELD_SELL_SIZE * 2 + center));
        }
        return new GameObjects(wallSet,boxSet,homeSet,player);
    }
}
