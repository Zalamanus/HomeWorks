package com.javarush.test.fifteen.model;

import java.util.*;

import static com.javarush.test.fifteen.model.Model.FIELD_SELL_SIZE;

/**
 * Created by MVTitov on 22.09.2016.
 */
public class LevelLoader {

    public GameObjects getLevel() {
        int offset = FIELD_SELL_SIZE / 2;

        NullBox nullBox = new NullBox(-100,-100);
        List<Box> boxList = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            boxList.add(new Box(-100,-100,i));
        }
        boxList.add(nullBox);
        Collections.shuffle(boxList);
        int i = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                boxList.get(i).set(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset);
                i++;
            }

        }

        return new GameObjects(boxList, nullBox, 4,4 );

    }
}
