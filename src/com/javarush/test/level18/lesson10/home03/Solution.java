package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream f1 = new FileOutputStream(reader.readLine());
        FileInputStream f2 = new FileInputStream(reader.readLine());
        FileInputStream f3 = new FileInputStream(reader.readLine());
        while (f2.available() > 0) {
            f1.write(f2.read());
        }
        f2.close();
        while (f3.available() > 0) {
            f1.write(f3.read());
        }
        f3.close();f1.close();
        reader.close();
    }
}
