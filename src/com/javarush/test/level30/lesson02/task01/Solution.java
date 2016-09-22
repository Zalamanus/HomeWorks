package com.javarush.test.level30.lesson02.task01;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16A")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        if (s.matches("^0x[0-9,a-f,A-F]+$")) return Integer.toString(Integer.parseInt(s.substring(2),16));
        else if (s.matches("^0[0-7]+$")) return Integer.toString(Integer.parseInt(s.substring(1),8));
        else if (s.matches("^0b[0-1]+$")) return Integer.toString(Integer.parseInt(s.substring(2),2));
        else if (s.matches("^[0-9]+$")) return s;
        return null;
    }
}
