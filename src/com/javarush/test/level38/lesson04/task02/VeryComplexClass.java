package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/

import java.util.HashSet;
import java.util.Set;

public class VeryComplexClass {
    public static void main(String[] args) {
        //new VeryComplexClass().methodThrowsClassCastException();
        new VeryComplexClass().methodThrowsNullPointerException();
    }
    public void methodThrowsClassCastException() {
        Object a = new Integer(1);
        String b = (String) a;
    }

    public void methodThrowsNullPointerException() {
        Set a = new HashSet<>(null);
    }
}
