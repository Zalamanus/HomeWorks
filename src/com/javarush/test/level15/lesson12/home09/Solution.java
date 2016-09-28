package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null, s2 =null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s = s.substring(s.indexOf("?")+1);
        String[] var = s.split("&");
        s = "";
        for (String s1 : var) {
            String[] var2 = s1.split("=");
            s += var2[0]+" ";
            if (var2[0].equals("obj")) s2 = var2[1];
        }
        System.out.println(s.trim());
        if (s2 != null) {
            try {
                alert(Double.parseDouble(s2));
            } catch (NumberFormatException e) {
                alert(s2);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
