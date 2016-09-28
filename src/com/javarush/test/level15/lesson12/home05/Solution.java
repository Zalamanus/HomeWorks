package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    private Solution(int a) {
    }
    private Solution(Integer b) {
    }
    private Solution(long b) {
    }

    public Solution(Object a) {
    }
    public Solution() {
    }
    public Solution(Short a) {
    }

    protected Solution(String b) {

    }
    protected Solution(Long b) {

    }
    protected Solution(Double b) {

    }

    Solution(Boolean b) {

    }
    Solution(boolean b) {

    }
    Solution(double b) {

    }

}

