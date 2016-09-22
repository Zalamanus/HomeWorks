package com.javarush.test.level22.lesson09.task03;

import java.io.*;
import java.util.Arrays;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder s = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader(reader.readLine()))) {
            while (f.ready()) {
                s.append(f.readLine());
                s.append(" ");
            }
        }
        String[] arr = s.toString().split("\\s+");

        StringBuilder result = getLine(arr);
        System.out.println(result.toString());
        System.out.println(arr[1]);
    }

    public static StringBuilder getLine(String... words) {
        String[] wordss= Arrays.copyOf(words,words.length);
        StringBuilder s = new StringBuilder();
        if (wordss==null && wordss.length==0) return null;
        if (wordss.length==1) return s.append(wordss[0]);
        char firstLetter=' ';
        char lastLetter=' ';
        int ii=0;
        char fLetter;
        char lLetter;
        boolean added = true;
        for (int i = 0; added || ii<wordss.length ; i++) {
            if (i == wordss.length) { i=0; ii++;}
            added = false;
            if (wordss[i].equals(" ") || wordss[i].equals("")) continue;
            String s1 = wordss[i];
            if (lastLetter==' ') {
                s.append(s1);
                added = true;
                wordss[i]=" ";
                lastLetter=s1.toUpperCase().charAt(s1.length()-1);
                firstLetter=s1.toUpperCase().charAt(0);
            } else {
                fLetter = s1.toUpperCase().charAt(0);
                lLetter = s1.toUpperCase().charAt(s1.length()-1);
                if (lLetter==firstLetter) {
                    s.insert(0,s1+" ");
                    added=true;
                    firstLetter=fLetter;
                    wordss[i]=" ";

                } else if (fLetter==lastLetter) {
                    s.append(" "+s1);
                    added=true;
                    lastLetter=lLetter;
                    wordss[i]=" ";

                }
            }


        }
          return s;
    }
}
