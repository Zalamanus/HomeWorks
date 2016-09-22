package com.javarush.test.level26.lesson02.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        Integer[] newarray = Arrays.copyOf(array, array.length);
        int middle = newarray.length / 2;
        Arrays.sort(newarray);
        final double mediana;
        if (newarray.length % 2 == 0) {
            mediana = (newarray[middle - 1] + newarray[middle]) / 2.0;
        } else
            mediana = newarray[middle];
        System.out.println(mediana);

        Comparator<Integer> compareToMed = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int res1 = Math.abs((int) (o1 - mediana));
                int res2 = Math.abs((int) (o2 - mediana));
                return res1 - res2;
            }
        };
        Arrays.sort(newarray, compareToMed);
        return newarray;
    }


}
