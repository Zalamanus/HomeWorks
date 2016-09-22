package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        ArrayList<String>[] listarr = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            listarr[i] = new ArrayList<>();
        }
        listarr[0].add("asdf");
        listarr[0].add("asdfasd");
        listarr[0].add("asdghf");
        listarr[1].add("ghasdf");
        listarr[1].add("aghjsdfasd");
        listarr[1].add("asdesraghf");
        listarr[2].add("ghaklj jsdf");
        listarr[2].add("aghjsd ddfasd");
        listarr[2].add("asdesr aghf");
        listarr[3].add("ghaklj jsdf ффыва");
        listarr[3].add("aghjsd ddfфывафываasd");
        listarr[3].add("asdesr aghвапывпf");


        return listarr;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}