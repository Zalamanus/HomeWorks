package com.javarush.test.level19.lesson03.task04;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner sc;

        public PersonScannerAdapter(Scanner sc) {
            this.sc = sc;
        }

        @Override
        public Person read() throws IOException {
            String s = sc.nextLine();
            String[] str = s.split(" ");
            SimpleDateFormat dateformat = new SimpleDateFormat("dd MM y");
            Date d = new Date();
            try {
                d = dateformat.parse(str[3] +" " + str[4] +" " + str[5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return new Person(str[1],str[2],str[0],d);
        }

        @Override
        public void close() throws IOException {
            sc.close();
        }

        public static void main(String[] args) throws IOException {
            File f = new File("1.txt");
            Scanner scanner = new Scanner(f);
            PersonScanner ps = new PersonScannerAdapter(scanner);
            System.out.println(ps.read());
            System.out.println(ps.read());
            System.out.println(ps.read());
        }
    }
}
