package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String f1 = reader.readLine();
        String f2 = reader.readLine();
        String f3 = reader.readLine();
        reader.close();
        FileInputStream is = new FileInputStream(f1);
        FileOutputStream os1 = new FileOutputStream(f2);
        FileOutputStream os2 = new FileOutputStream(f3);
        int f3len=is.available()/2;
        int f2len= (is.available()%2==0) ? f3len : f3len+1;
        byte[] f2Buf = new byte[f2len];
        byte[] f3Buf = new byte[f3len];
        is.read(f2Buf,0,f2len);
        is.read(f3Buf,0,f3len);
        os1.write(f2Buf);
        os2.write(f3Buf);
        is.close();
        os1.close();os2.close();

    }
}
