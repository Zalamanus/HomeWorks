package com.javarush.test.level04.lesson04.task10;

/* Три числа
Ввести с клавиатуры три целых числа. Определить, имеется ли среди них хотя бы одна пара равных между собой чисел.
Если такая пара существует, вывести на экран числа через пробел. Если все три числа равны между собой, то вывести все три.
Пример для чисел 1 2 2:
2 2
Пример для чисел 2 2 2:
2 2 2
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sA = reader.readLine();
        int nA = Integer.parseInt(sA);
        String sB = reader.readLine();
        int nB = Integer.parseInt(sB);
        String sC = reader.readLine();
        int nC = Integer.parseInt(sC);
        if ((nA == nB) && (nA == nC)) System.out.println(nA+" "+nA+" "+nA);
        else if (nA == nB) System.out.println(nA+" "+nA);
        else if (nA == nC) System.out.println(nA+" "+nA);
        else if (nC == nB) System.out.println(nB+" "+nB);

    }
}