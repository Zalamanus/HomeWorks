package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла,
генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String f = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(f));
        Pattern p = Pattern.compile("^\\d{1,8}");
        Matcher m;
        int max=0;
        while (reader.ready()) {
            m = p.matcher(reader.readLine());
            if (m.find()) {
                int id = Integer.parseInt(m.group());
                if (id > max) max = id;
            }
        }
        reader.close();
        if (args[0].equals("-c")) {
            String s = arrayToString(args);
            p = Pattern.compile("(\\p{IsAlphabetic}+[\\p{IsAlphabetic}|\\s]*)\\s(\\d+.?\\d{0,2})\\s(\\d+)");
            m = p.matcher(s);
            while (m.find()) {
                createEntry(m.group(1),Double.parseDouble(m.group(2)),Integer.parseInt(m.group(3)),f,max);
            }
        }
    }

    private static void createEntry(String group, Double group1, Integer group2, String f, int max) throws IOException {
        OutputStream os = new FileOutputStream(f,true);
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        System.out.printf(Locale.ENGLISH,"\n%-8d%-30.30s%-8.2f%-4d",max+1,group,group1,group2);
        ps.close();os.close();

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
