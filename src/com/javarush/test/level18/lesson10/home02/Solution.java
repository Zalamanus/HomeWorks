package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream reader = new FileInputStream(args[0]);
        int space=0;
        int allchar =0;
        while (reader.available()>0) {
            int ch = reader.read();
            allchar++;
            if (ch == ' ') space++;
        }
        double i = ((double)space)/allchar * 100;
        System.out.println(Math.rint(i*100)/100);
        reader.close();
    }
}
