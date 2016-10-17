package com.javarush.test.fifteen.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

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
        level = 1;
        int offset = FIELD_SELL_SIZE / 2;

        // вычитываем нужный уровень
        NullBox nullBox = new NullBox(-30,-30);
        List<Box> boxList = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            boxList.add(new Box(-30,-30,i));
        }
        boxList.add(nullBox);
        Collections.shuffle(boxList);
        int i = 0;
        for (int y = 1; y < 5; y++) {
            for (int x = 1; x < 5; x++) {
                boxList.get(i).set(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset);
                i++;
            }

        }

        List<Wall> wallList = new ArrayList<>();


        int levelHeight = 0;
        int levelWidth = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            while (reader.ready()) {
                String s = reader.readLine();
                String[] properties = s.split(": ");
                if (properties[0].equalsIgnoreCase("Maze")) {
                    int fileLevel = Integer.parseInt(properties[1]);
                    if (fileLevel == level) {
                        while (reader.ready()) {
                            s = reader.readLine();
                            if (!s.contains(": ")) break;
                            properties = s.split(": ");
                            if (properties[0].equalsIgnoreCase("Size Y")) {
                                levelHeight = Integer.parseInt(properties[1]);
                            }
                            if (properties[0].equalsIgnoreCase("Size X")) {
                                levelWidth = Integer.parseInt(properties[1]);
                            }


                        }
                        while (reader.ready()) {
                            for (int y = 0; y < levelHeight; y++) {
                                s = reader.readLine();
                                char[] line = s.toCharArray();
                                for (int x = 0; x < line.length; x++) {
                                    switch (line[x]) {
                                        case 'X':
                                            wallList.add(new Wall(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset));
                                            break;
                                        /*case '1':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,1));
                                            break;
                                        case '2':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,2));
                                            break;
                                        case '3':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,3));
                                            break;
                                        case '4':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,4));
                                            break;
                                        case '5':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,5));
                                            break;
                                        case '6':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,6));
                                            break;
                                        case '7':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,7));
                                            break;
                                        case '8':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,8));
                                            break;
                                        case '9':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,9));
                                            break;
                                        case 'a':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,10));
                                            break;
                                        case 'b':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,11));
                                            break;
                                        case 'c':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,12));
                                            break;
                                        case 'd':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,13));
                                            break;
                                        case 'e':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,14));
                                            break;
                                        case 'f':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset,15));
                                            break;
                                        case '0':
                                            nullBox = new NullBox(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset);
                                            boxSet.add(nullBox);
                                            break;*/
                                    }
                                }

                            }
                            break;


                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Some exception while level search was occured.");
        }
        return new GameObjects(wallList, boxList, nullBox, levelWidth,levelHeight,level);

/*
        int center = FIELD_SELL_SIZE / 2;
        Player player = new Player(FIELD_SELL_SIZE * 0 + center, FIELD_SELL_SIZE * 0 + center);
        Set<Home> homeSet = new HashSet<>();
        homeSet.add(new Home(FIELD_SELL_SIZE * 3 + center, FIELD_SELL_SIZE * 3 + center));
        homeSet.add(new Home(FIELD_SELL_SIZE * 4 + center, FIELD_SELL_SIZE * 3 + center));
        Set<Box> boxSet = new HashSet<>();
        boxSet.add(new Box(FIELD_SELL_SIZE * 1 + center, FIELD_SELL_SIZE * 1 + center));
        boxSet.add(new Box(FIELD_SELL_SIZE * 2 + center, FIELD_SELL_SIZE * 1 + center));
        Set<Wall> wallList = new HashSet<>();
        for (int i = 2; i < 6; i++) {
            wallList.add(new Wall(FIELD_SELL_SIZE * i + center, FIELD_SELL_SIZE * 2 + center));
        }
        return new GameObjects(wallList, boxSet, homeSet, player);
*/
    }
}
