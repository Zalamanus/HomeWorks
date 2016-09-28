package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder s = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader(reader.readLine()))) {
            while (f.ready()) {
                s.append(f.readLine());
                s.append(" ");
            }
        }
        String[] arr = s.toString().split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==null) continue;
            StringBuilder s1 = new StringBuilder(arr[i]);
            s1.reverse();
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]==null) continue;
                StringBuilder s2 = new StringBuilder(arr[j]);
                if (s2.toString().equals(s1.toString())) {
                    result.add(new Pair(s1.reverse().toString(),s2.toString()));
                    arr[i]=null;
                    arr[j]=null;
                    break;
                }
            }
        }

    }

    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
