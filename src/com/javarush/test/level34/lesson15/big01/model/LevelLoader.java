package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileReader;
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
        int maxLevel = 0;
        // вычитываем весь файл, узнаем максимальный уровень
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            while (reader.ready()) {
                String s = reader.readLine();
                String[] properties = s.split(": ");
                if (properties[0].equalsIgnoreCase("Maze")) {
                    int fileLevel = Integer.parseInt(properties[1]);
                    maxLevel = Math.max(fileLevel, maxLevel);
                }
            }
        } catch (Exception e) {
            System.out.println("Some exception while reading file was occured.");
        }
        if (level > maxLevel) level = level % maxLevel;

        // вычитываем нужный уровень
        Set<Home> homeSet = new HashSet<>();
        Set<Box> boxSet = new HashSet<>();
        Set<Wall> wallSet = new HashSet<>();
        Player player = null;
        int offset = FIELD_SELL_SIZE / 2;

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
                                            wallSet.add(new Wall(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset));
                                            break;
                                        case '*':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset));
                                            break;
                                        case '.':
                                            homeSet.add(new Home(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset));
                                            break;
                                        case '&':
                                            boxSet.add(new Box(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset));
                                            homeSet.add(new Home(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset));
                                            break;
                                        case '@':
                                            player = new Player(FIELD_SELL_SIZE * x + offset, FIELD_SELL_SIZE * y + offset);
                                            break;
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
        return new GameObjects(wallSet, boxSet, homeSet, player,levelWidth,levelHeight,level);

/*
        int center = FIELD_SELL_SIZE / 2;
        Player player = new Player(FIELD_SELL_SIZE * 0 + center, FIELD_SELL_SIZE * 0 + center);
        Set<Home> homeSet = new HashSet<>();
        homeSet.add(new Home(FIELD_SELL_SIZE * 3 + center, FIELD_SELL_SIZE * 3 + center));
        homeSet.add(new Home(FIELD_SELL_SIZE * 4 + center, FIELD_SELL_SIZE * 3 + center));
        Set<Box> boxSet = new HashSet<>();
        boxSet.add(new Box(FIELD_SELL_SIZE * 1 + center, FIELD_SELL_SIZE * 1 + center));
        boxSet.add(new Box(FIELD_SELL_SIZE * 2 + center, FIELD_SELL_SIZE * 1 + center));
        Set<Wall> wallSet = new HashSet<>();
        for (int i = 2; i < 6; i++) {
            wallSet.add(new Wall(FIELD_SELL_SIZE * i + center, FIELD_SELL_SIZE * 2 + center));
        }
        return new GameObjects(wallSet, boxSet, homeSet, player);
*/
    }
}
