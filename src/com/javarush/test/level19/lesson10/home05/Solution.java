package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader f1 = new BufferedReader(new FileReader(args[0]));
        BufferedWriter f2 = new BufferedWriter(new FileWriter(args[1]));
        while (f1.ready()) {
            String[] s = f1.readLine().split(" ");
            String res = "";
            for (int i = 0; i < s.length; i++) {
                if (s[i].matches(".*\\d+.*"))
                    res += s[i]+" ";
            }

            f2.write(res.trim()+"\n");

        }
        f1.close();
        f2.close();
    }
}
