package com.javarush.test.level25.lesson02.task02;

import java.util.*;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {

    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            Wheel[] list = Wheel.values();
            HashSet<Wheel> set = new HashSet<>();
            if (loadWheelNamesFromDB().length!=4) {
                System.out.println("Это не машина");
                return;
            }
            for (String s : loadWheelNamesFromDB()) {
                try {
                    set.add(Wheel.valueOf(s));
                } catch (IllegalArgumentException e) {
                    System.out.println("Это не машина");
                }
            }
            if (set.containsAll(Arrays.asList(list))) wheels = Arrays.asList(list);
            else System.out.println("Это не машина");
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "BACK_LEFT", "BACK_RIGHT", "FRONT_RIGHT", };
        }
    }
}
