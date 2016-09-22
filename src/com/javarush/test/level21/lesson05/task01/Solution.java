package com.javarush.test.level21.lesson05.task01;

import java.util.HashSet;
import java.util.Set;

/* Equals and HashCode
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (this.hashCode() != n.hashCode()) return false;
        if (n.getClass() == this.getClass()) {
            Solution o = (Solution) n;
            if (o.first==null && first==null && o.last!=null && last!=null) return o.last.equals(last) ;
            else if (o.last==null && last==null && o.first!=null && first!=null) return o.first.equals(first);
            else if (o.first!=null && o.last!=null && first!=null && last!=null) return o.first.equals(first) && o.last.equals(last);
            else if (o.first==null && o.last==null && first==null && last==null) return true;
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
        s.add(new Solution(null, "djd"));
        System.out.println(s.contains(new Solution(null, "djdf")));
    }
}
