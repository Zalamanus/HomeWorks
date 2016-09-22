package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream in = new FileInputStream(reader.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        int j=0;
        while (in.available()>0) {
            char i = (char) in.read();
            if ( i != '\n' ) s.append(i);
            else {
                j = Integer.parseInt(s.toString());
                if (j%2==0) list.add(j);
                s.delete(0,s.length());

            }
        }
        if (s.length()>0) {
            j = Integer.parseInt(s.toString());
            if (j%2==0) list.add(j);
        }

        boolean changed =true;
        while (changed) {
            changed = false;
            for (int i = 0; i < list.size()-1; i++) {
                int a = list.get(i);
                int b = list.get(i + 1);
                if (a>b) {
                    list.set(i + 1, a);
                    list.set(i, b);
                    changed = true;
                }
            }
        }
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }
}
