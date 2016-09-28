package com.javarush.test.level20.lesson10.bonus02;

import java.util.Arrays;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {

        byte[][] temp = new byte[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                temp[i][j]=a[i][j];
            }
        }
        int count =0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (temp[i][j] == 1) {
                    count++;
                    int a1=i,b=j;
                    int c=i,d=j;
                    for (int k = i; k < temp.length; k++) {
                        if (temp[k][j]==1) {
                            c=k;

                        } else break;
                    }
                    for (int k = j; k < temp[0].length; k++) {
                        if (temp[i][k] == 1) {
                            d=k;
                        } else break;
                    }
                    for (int k = a1; k <= c; k++) {
                        for (int l = b; l <= d; l++) {
                            temp[k][l]=0;
                        }
                    }
                }
            }
        }

        return count;
    }
}
