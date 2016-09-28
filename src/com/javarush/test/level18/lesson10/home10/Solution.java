package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {

        Pattern p = Pattern.compile("^(.+)\\.part(\\d+)$");

        TreeSet<FileName> set = new TreeSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=reader.readLine()).equals("end")) {
            set.add(new FileName(s));
        }
        reader.close();



        Matcher m = p.matcher(set.first().s);
        m.matches();
        FileInputStream is;
        FileOutputStream os = new FileOutputStream(m.group(1));
        byte[] buff = new byte[1000];
        for (FileName s1 : set) {
            is = new FileInputStream(s1.s);
            while (is.available()>0) {

                int len = is.read(buff);
                os.write(buff,0,len);
            }
            is.close();
        }
        os.close();

    }
    public static class FileName implements Comparable<FileName> {
        String s;

        public FileName(String s) {
            this.s = s;
        }

        @Override
        public int compareTo(FileName fileName) {
            Pattern pat = Pattern.compile(".*part(\\d+)$");
            Matcher matS = pat.matcher(fileName.s);
            Matcher matThis = pat.matcher(this.s);
            matS.matches();
            matThis.matches();
            int intS = Integer.parseInt(matS.group(1));
            int intThis = Integer.parseInt(matThis.group(1));
            if (intS > intThis) return -1;
            if (intS < intThis) return 1;
            return 0;
        }
    }
}
