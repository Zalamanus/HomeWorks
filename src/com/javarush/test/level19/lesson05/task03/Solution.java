package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter f2 = new BufferedWriter(new FileWriter(reader.readLine()));
        reader.close();
        Pattern p = Pattern.compile("\\b\\d+\\b");
        Matcher m;
        while (f1.ready()) {
            m = p.matcher(f1.readLine());
            while (m.find()) {
                f2.write(m.group());
                f2.write(" ");
            }
        }
        f1.close();f2.close();
    }
}
