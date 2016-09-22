package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader f1 = new BufferedReader(new FileReader(args[0]));
        BufferedWriter f2 = new BufferedWriter(new FileWriter(args[1]));
        ArrayList<String> list = new ArrayList<>();
        while (f1.ready()) {
            String[] arr = f1.readLine().split(" ");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length()>6) list.add(arr[i]);

            }
        }
        f1.close();
        for (int i = 0; i < list.size(); i++) {
            if (i!=list.size()-1) f2.write(list.get(i)+",");
            else f2.write(list.get(i));
        }
        f2.close();
    }
}
