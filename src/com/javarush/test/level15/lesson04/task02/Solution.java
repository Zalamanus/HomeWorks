package com.javarush.test.level15.lesson04.task02;

/* ООП - Перегрузка
Перегрузите метод printMatrix 8 различными способами. В итоге должно получиться 10 различных методов printMatrix.
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
        printMatrix(2, 3, new Integer(8));
        printMatrix(2, 3, new Short((short) 8));
        printMatrix(2, 3, new Double((double) 8));
        printMatrix(2, 3, new Long((long) 8));
        printMatrix(2, 3, new Float((float) 8));
        printMatrix(2, 3, new Byte((byte) 8));
        printMatrix(2, 3, new Character((char) 8));
        printMatrix(2, 3, new Boolean(true));
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Integer value) {
        System.out.println("Заполняем объектами Integer");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Short value) {
        System.out.println("Заполняем объектами Short");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Double value) {
        System.out.println("Заполняем объектами Double");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Long value) {
        System.out.println("Заполняем объектами Long");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Float value) {
        System.out.println("Заполняем объектами Float");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Byte value) {
        System.out.println("Заполняем объектами Byte");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Character value) {
        System.out.println("Заполняем объектами Character");
        printMatrix(m, n, (Object) value);
    }
    public static void printMatrix(int m, int n, Boolean value) {
        System.out.println("Заполняем объектами Boolean");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }
}
