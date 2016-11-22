package com.javarush.test.level36.lesson08.bonus01;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* Разбираемся в красно-черном дереве
Дана реализация красно-черного дерева.
Некоторые методы сломаны. Разберитесь в коде и исправьте ошибки.
Метод main не участвует в тестировании.
Все модификатры правильные.
Имена переменных и методов не изменяйте.
*/
public class Solution {
    public static void main(String[] args) {
        Set<Integer> map = new TreeSet<>();
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(9);
        tree.insert(4);
        tree.insert(8);
        tree.insert(1);
        tree.insert(5);
        tree.insert(3);
        try {
            NodeHelperTestSolution.getNodeValue("element",tree.current);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
