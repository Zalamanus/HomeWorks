package com.javarush.test.level05.lesson12.bonus02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Нужно добавить в программу новую функциональность
Задача: Программа вводит два числа с клавиатуры и выводит минимальное из них на экран.
Новая задача: Программа вводит пять чисел с клавиатуры и выводит минимальное из них на экран.
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Minimum min = new Minimum();
        min.add(Integer.parseInt(reader.readLine()));
        min.add(Integer.parseInt(reader.readLine()));
        min.add(Integer.parseInt(reader.readLine()));
        min.add(Integer.parseInt(reader.readLine()));
        min.add(Integer.parseInt(reader.readLine()));


        System.out.println("Minimum = " + min.value);
    }

    static class Minimum {
        int value = 0;

        public void add(int n) {
            value = (n < value) ? n : value;
        }

    }


}
