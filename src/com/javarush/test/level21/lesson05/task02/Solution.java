package com.javarush.test.level21.lesson05.task02;

import java.util.HashSet;
import java.util.Set;

/* Исправить ошибку
Сравнение объектов Solution не работает должным образом. Найти ошибку и исправить.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (this.hashCode() != o.hashCode()) return false;
        if (o.getClass() == this.getClass()) {
            Solution n = (Solution) o;
            if (n.first==null && first==null && n.last!=null && last!=null) return n.last.equals(last) ;
            else if (n.last==null && last==null && n.first!=null && first!=null) return n.first.equals(first);
            else if (n.first!=null && n.last!=null && first!=null && last!=null) return n.first.equals(first) && n.last.equals(last);
            else if (n.first==null && n.last==null && first==null && last==null) return true;
            else return false;
        } else return false;
    }
    public int hashCode() {
        if (first != null && last != null)
            return first.length() + last.length();
        else if (first == null && last != null)
            return last.length();
        else if (first != null && last == null) return first.length();
        else return 0;

    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
