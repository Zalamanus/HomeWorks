package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();
        String s2 = reader.readLine();
        FileReader f1 = new FileReader(s1);
        FileWriter f2 = new FileWriter(s2);
        int i = 0;
        while (f1.ready()) {
            i++;
            int y = f1.read();
            if (i % 2 == 0) f2.write(y);
        }
        f1.close();
        f2.close();

    }
}
