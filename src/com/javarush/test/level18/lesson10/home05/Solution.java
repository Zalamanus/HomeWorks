package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader f1 = new FileReader(reader.readLine());
        FileWriter f2 = new FileWriter(reader.readLine());
        reader.close();
        StringBuilder s = new StringBuilder("");
        while (f1.ready()) {
            s.append((char) f1.read());
        }
        f1.close();
        String[] str = s.toString().split(" ");
        for (int i = 0; i < str.length; i++) {
            int y = Math.round(Math.round(Double.parseDouble(str[i])));
            f2.write(String.valueOf(y));
            if (i!=str.length-1) f2.write(" ");
        }
        f2.close();
    }
}
