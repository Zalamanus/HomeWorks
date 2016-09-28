package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution a = new Solution();
        a.innerClasses[0] = a.new InnerClass();
        a.innerClasses[1] = a.new InnerClass();
        Solution b = new Solution();
        b.innerClasses[0] = b.new InnerClass();
        b.innerClasses[1] = b.new InnerClass();

        return new Solution[]{a,b};
    }
}
