package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        String[] arr = s.split(" ");
        s="";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length()>1) {
                String first = arr[i].substring(0,1).toUpperCase();
                String all = arr[i].substring(1);
                arr[i]=first+all;
            }
            s += arr[i]+" ";
        }
        s = s.trim();
        System.out.println(s);
    }


}
