package com.javarush.test.level19.lesson08.task02;

/* Ридер обертка 2
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна заменять все подстроки "te" на "??"
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Вывести модифицированную строку в консоль.
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream cons = System.out;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        testString.printSomething();
        String res = os.toString().replaceAll("te", "??");
        System.setOut(cons);
        System.out.println(res);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
    }
    }
}
