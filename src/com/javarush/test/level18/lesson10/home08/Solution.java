package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        while (!(s = reader.readLine()).equals("exit")) {
            if (!s.equals("")) new ReadThread(s).start();
        }
        reader.close();

    }

    public static class ReadThread extends Thread {
        private FileInputStream f;
        private int[] arr = new int[256];

        public ReadThread(String fileName) throws FileNotFoundException {
            super(fileName);
            f = new FileInputStream(fileName);
        }

        @Override
        public void run() {
            try {
                while (f.available() > 0) {
                    int i = f.read();
                    arr[i]++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int max = 0, bait = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                    bait = i;
                }
            }
            resultMap.put(Thread.currentThread().getName(), bait);
        }

    }
}
