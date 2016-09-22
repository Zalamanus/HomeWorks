package com.javarush.test.level09.lesson11.home04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* Конвертер дат
Ввести с клавиатуры дату в формате «08/18/2013»
Вывести на экран эту дату в виде «AUG 18, 2013».
Воспользоваться объектом Date и SimpleDateFormat.
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        Date date = new Date();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split("/");
        date.setMonth(Integer.parseInt(s[0])-1);
        date.setDate(Integer.parseInt(s[1]));
        date.setYear(Integer.parseInt(s[2])-1900);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY", Locale.ENGLISH);
        String s1 = dateFormat.format(date);
        String s2 = s1.substring(0,3).toUpperCase();
        String s3 = s1.substring(3);
        s1 = s2 +s3;
        System.out.println(s1);
    }
}
