package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String f1str = reader.readLine();
        FileInputStream f1 = new FileInputStream(f1str);
        FileInputStream f2 = new FileInputStream(reader.readLine());
        reader.close();
        byte[] buff = new byte[f1.available()];
        f1.read(buff);
        f1.close();
        FileOutputStream f3 = new FileOutputStream(f1str);
        while (f2.available() > 0) {
            f3.write(f2.read());
        }
        f2.close();
        f3.write(buff);
        f3.close();
    }
}
