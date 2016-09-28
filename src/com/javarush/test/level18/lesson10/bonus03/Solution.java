package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static TreeMap<Integer, String> map = new TreeMap<>();
    public static void main(String[] args) throws Exception {
        if (!(args[0].equals("-u")||args[0].equals("-d"))) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String f = reader.readLine();
        reader.close();
        Pattern p = Pattern.compile("^(\\d{1,8})\\s*(.*)$");
        Matcher m;
        reader = new BufferedReader(new FileReader(f));
        while (reader.ready()) {
            m = p.matcher(reader.readLine());
            m.find();
            map.put(Integer.parseInt(m.group(1)), m.group(2));
        }
        reader.close();
        if (args[0].equals("-d")) {
            map.remove(Integer.parseInt(args[1]));
            map2File(map, f);
        }
        if (args[0].equals("-u")) {
            String s = arrayToString(args);
            p = Pattern.compile("^(\\d{1,8})\\s(\\p{IsAlphabetic}+[\\p{IsAlphabetic}|\\s]*)\\s(\\d+.?\\d{0,2})\\s(\\d+)");
            m = p.matcher(s);
            while (m.find()) {
                map.put(Integer.parseInt(m.group(1)),String.format(Locale.ENGLISH, "%-30.30s%-8.2f%-4d", m.group(2), Double.parseDouble(m.group(3)), Integer.parseInt(m.group(4))));
            }

            map2File(map, f);
        }
    }

    private static void map2File(TreeMap<Integer, String> map, String f) throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter(f));
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            file.write(String.format(Locale.ENGLISH,"%-8d%s\n",entry.getKey(),entry.getValue()));
        }
        file.close();
    }
    public static String arrayToString(String[] arr) {
        String s="";
        for (int i = 1; i < arr.length; i++) {
            s += arr[i];
            if (i!=arr.length-1) s += " ";


        }
        return s;
    }

}
