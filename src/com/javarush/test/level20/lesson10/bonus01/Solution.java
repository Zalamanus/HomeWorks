package com.javarush.test.level20.lesson10.bonus01;


import java.util.*;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
/*
    public static void main(String[] args) {
        Date start = new Date();
        int[] test = getNumbers(372);
        Date finish = new Date();
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
        System.out.println((finish.getTime() - start.getTime()) / 1000);

    }
*/

    public static int[] getNumbers(int N) {
        //из-за специфики перебираемых чисел 148800-148880-148888-148889-148890-148899 и т.д.
        //существет проблема с проверкой 19тизначных чисел, (они выходят за пределы Long.Max_value)
        //поэтому ограничиваю проверку 18тизначными числами
        if (Math.log10(N) > 10) N = 1999999990;
        //создаю массив, куда будет сохраняться числа от 0 до 9 и их степени от 0 до 19
        //prime_power[3][11] = 3^11 = 177147
        //степень вычисляем только 1 раз, дальше берем из памяти
        int[][] prime_power = new int[10][20];
        //сюда сохраняем найденные числа армстронга
        ArrayList<Integer> result_array = new ArrayList<>();
        for (int i = 1; i <= N; i = nextI(i)) {
            //раскладываем i на массив чисел
            int[] tempArray = numberToArray(i);
            //подготавливаемся считать сумму
            int summ = 0;
            //перебираем каждое число в массиве
            for (int current_number : tempArray)
                //если число 0 - считать ничего не нужно, сумма степеней не меняется
                if (current_number != 0) {
                    //если текущее число возводится в степень впервые - возводим и сохраняем в массив
                    if (prime_power[current_number][tempArray.length] == 0) {
                        //здесь будем считать степень
                        int pow = 1;
                        //Math.pow не использовал, т.к. на больших числах начинаются ошибки
                        //(long)Math.pow(9,17) выводит 16677181699666570 вместо правильного 16677181699666569
                        for (int j = 0; j < tempArray.length; j++)
                            pow *= current_number;
                        prime_power[current_number][tempArray.length] = pow;
                    }
                    summ += prime_power[current_number][tempArray.length];
                }
            //если сумма не превышает введеного изначально значения,
            //и если их разность кратна 9ти (разность чисел, состоящих из одних и тех же цифер
            //    всегда кратна 9ти, и если этого не соблюдается - не лезем в функцию compareArrays -
            //    еще немного оптимизации алгоритма)
            //и если и сумма и long i состоят из одних и тех же цифер - записываем в массив
            if (summ <= N && (summ - i) % 9 == 0 && compareArrays(tempArray, numberToArray(summ)))
                result_array.add(summ);
        }
        // сортируем коллекцию и перебразовываем все числа в массив, как требует задача
        int[] result = new int[result_array.size()];
        Collections.sort(result_array);
        for (int i = 0; i < result.length; i++)
            result[i] = result_array.get(i);
        return result;
    }

    public static int nextI(int i) {
        if ((i + 1) % 10 != 1 || i == 0) return ++i;
        else {
            int copy_i = i;
            int count = 1;
            while (copy_i % 10 == 0) {
                copy_i /= 10;
                count *= 10;
            }
            count = copy_i % 10 * count / 10;
            return i += count;
        }
    }

    public static int[] numberToArray(int n) {
        int capacity = 1;
        if (n != 0) capacity = (int) Math.log10(n) + 1;
        int[] numbers = new int[capacity];
        for (int i = capacity - 1; i >= 0; i--, n /= 10)
            numbers[i] = (int) (n % 10);
        return numbers;
    }

    public static boolean compareArrays(int[] first, int[] second) {
        if (first.length != second.length) return false;
        for (int i = 0; i < first.length; i++) {
            boolean contains = false;
            for (int j = 0; j < second.length; j++) {
                if (first[i] == second[j]) {
                    second[j] = -1;
                    contains = true;
                    break;
                }
            }
            if (!contains) return false;
        }
        return true;
    }

    public static void main(String args[]) {
        Long t0 = System.currentTimeMillis();
        int[] result = getNumbers(1000000000);
        Long t1 = System.currentTimeMillis();
        System.out.println("Time need to create the array = " + (t1 - t0));
        System.out.println("Used Memory in JVM: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576 + " Mb");
        int k = 1;
        for (int i : result)
            System.out.println(k++ + ". " + i);
    }
}
