package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream f1 = new FileInputStream(reader.readLine());
        FileOutputStream f2 = new FileOutputStream(reader.readLine());
        byte[] buff = new byte[f1.available()];
        for (int i = buff.length-1; i >= 0; i--) {
            buff[i]=(byte) f1.read();
        }
            f2.write(buff);
        reader.close();
        f1.close();f2.close();
    }
}
