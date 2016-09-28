package com.javarush.test.level22.lesson13.task03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(checkTelNumber("+8(880)5-1-234566"));
    }

    public static boolean checkTelNumber(String telNumber) {
        Pattern p = Pattern.compile("^\\+(\\d+)$");
        Matcher m = p.matcher(telNumber);
        if (m.find())
            if (m.group(1).length() == 12) return true;
        p = Pattern.compile("^\\+(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() == 12) return true;
        p = Pattern.compile("^\\+(\\d+)-(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() + m.group(3).length() == 12) return true;
        p = Pattern.compile("^\\+(\\d+)\\(\\d{3}\\)(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() == 9) return true;
        p = Pattern.compile("^\\+(\\d+)\\(\\d{3}\\)(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() + m.group(3).length() == 9) return true;
        p = Pattern.compile("^\\+(\\d+)\\(\\d{3}\\)(\\d+)-(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() + m.group(3).length() + m.group(4).length() == 9) return true;
        p = Pattern.compile("^\\(\\d{3}\\)(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() == 7) return true;
        p = Pattern.compile("^\\(\\d{3}\\)(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() == 7) return true;
        p = Pattern.compile("^\\(\\d{3}\\)(\\d+)-(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() + m.group(3).length() == 7) return true;
        p = Pattern.compile("^(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() == 10) return true;
        p = Pattern.compile("^(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() == 10) return true;
        p = Pattern.compile("^(\\d+)-(\\d+)-(\\d+)$");
        m.usePattern(p);
        m.reset();
        if (m.find())
            if (m.group(1).length() + m.group(2).length() + m.group(3).length() == 10) return true;

        return false;
    }
}
