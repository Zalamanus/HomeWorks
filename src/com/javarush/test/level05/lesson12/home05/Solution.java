package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int sum = 0;
        Scanner scan = new Scanner(System.in);

        while (true) {
            if (scan.hasNextInt()) sum +=scan.nextInt();
            else if (scan.hasNext()) {
                if (scan.next().equals("сумма")) break;
            }
        }
        System.out.println(sum);
    }
}
