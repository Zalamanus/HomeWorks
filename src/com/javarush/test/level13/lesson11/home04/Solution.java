package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        ArrayList<String> list = new ArrayList<>();
        String s = "";
        while (!s.equals("exit")) {
            s = reader.readLine();
            list.add(s);
        }
        OutputStream out = new FileOutputStream(file);
        for (String s1 : list) {
            char[] arr = s1.toCharArray();
            for (char c : arr) {
                out.write((int) c);
            }
            out.write((int) '\n');
        }
    }
}
