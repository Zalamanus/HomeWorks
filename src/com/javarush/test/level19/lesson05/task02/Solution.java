package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        BufferedReader f = new BufferedReader(new FileReader(s));
        Pattern p  = Pattern.compile("\\bworld\\b");
        Matcher m;
        int res=0;
        while (f.ready()) {
            String s1 = f.readLine();
            m = p.matcher(s1);
            while (m.find()) res++;
        }
        System.out.println(res);
        reader.close();
        f.close();
    }
}
