package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(reader.readLine());
        int y = Integer.parseInt(reader.readLine());
        int min = (i < y) ? i : y;
        for (; min > 0; min--) {
            if ((i % min ==0) && (y % min == 0)) break;

        }
        System.out.println(min);
    }
}
