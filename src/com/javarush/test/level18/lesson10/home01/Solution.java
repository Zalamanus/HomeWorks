package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String letters = "awertyuioplkjhgfdsqzxcvbnm";
        FileReader fr = new FileReader(args[0]);
        char[] let = letters.toCharArray();
        int res=0;
        while (fr.ready()) {
           int y = fr.read();
            for (char c : let) {
                if ((c==y) || (Character.toUpperCase(c)==y)) res++;
            }
        }
        fr.close();
        System.out.println(res);
    }
}
