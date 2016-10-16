package com.javarush.test.level36.lesson08.task01;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Set<String> set = new TreeSet<>();
        try (BufferedReader reader = Files.newReader(new File(args[0]), Charset.defaultCharset()) ) {
            while (reader.ready()) {
                String s = reader.readLine().toLowerCase();
                for (int i = 0; i < s.length(); i++) {
                    String sub = s.substring(i,i+1);
                    if (sub.matches("\\p{IsAlphabetic}")) set.add(sub);
                }
            }
        }
        int i = 0;
        for (String s : set) {
            System.out.print(s);
            if (++i == 5) break;
        }
    }
}
