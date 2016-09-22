package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static {
        threads.add(new Thread(new Infinity()));
        threads.add(new Thread(new Interrupt()));
        threads.add(new Thread(new Wow()));
        threads.add(new Thread(new Msg()));
        threads.add(new Thread(new Reader()));
    }

/*
    public static void main(String[] args) {
        threads.get(1).start();
        threads.get(1).interrupt();
    }
*/

    public static class Infinity implements Runnable {

        @Override
        public void run() {
            while (true) {
            }
        }
    }

    public static class Interrupt implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class Wow implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static class Msg implements Runnable, Message {
        public boolean isCancel = false;

        @Override
        public void showWarning() {
            isCancel = true;
        }

        @Override
        public void run() {
            while (!isCancel) {
            }
        }
    }

    public static class Reader implements Runnable {

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = "";
            int result = 0;
            while (true) {
                try {
                    s = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s.equals("N")) break;
                result += Integer.parseInt(s);
            }
            System.out.println(result);
        }
    }
}