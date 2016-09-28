package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter f2 = new BufferedWriter(new FileWriter(reader.readLine()));
        reader.close();
        while (f1.ready()) {
            f2.write(f1.readLine().replaceAll("\\.", "!"));
            f2.write("\n");
        }
        f1.close();
        f2.close();

    }
}
