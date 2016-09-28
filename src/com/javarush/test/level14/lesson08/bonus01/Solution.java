package com.javarush.test.level14.lesson08.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        int[] arr = new int[0];
        try {
            arr = new int[-5];
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            arr[6]=5;
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            InputStream is = new FileInputStream("asdf");

        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            arr[0] = Integer.parseInt("asdf");
        } catch (NumberFormatException e) {
            exceptions.add(e);
        }
        ArrayList<Integer> list = new ArrayList<>();
        try {
            list.remove(2);
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            dump(null,5);
        } catch (Exception e) {
            exceptions.add(e);
        }
        String s = "asdf";
        try {
            s = s.substring(30);
        } catch (Exception e) {
            exceptions.add(e);
        }
        Object i = Integer.valueOf(42);
        try {
            s = (String)i;
        } catch (Exception e) {
            exceptions.add(e);
        }
        exceptions.add(new IllegalArgumentException());

    }
    public static void dump(PrintWriter pw, Integer obj) {
                 pw.print(obj);
        }
}
