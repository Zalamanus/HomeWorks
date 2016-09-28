package com.javarush.test.level20.lesson10.bonus04;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution extends AbstractList<String> implements Serializable, Cloneable, List<String> {
    transient int size;
    transient Solution.Node<String> first1;
    transient Solution.Node<String> first2;
    transient Solution.Node<String> last1;
    transient Solution.Node<String> last2;
    public Solution() {
        this.size = 0;
    }

    public static void main(String[] args) {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
    }

    public String getParent(String value) {
        //have to be implemented
        return null;
    }

    @Override
    public String get(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    public boolean add(String var1) {
        this.linkLast(var1);
        return true;
    }

    void linkLast(String var1) {
        Solution.Node var2 = null;
        if (this.last1 !=null) if (this.last1.next1 == null || this.last1.next2 == null) var2 = this.last1;
        else if (this.last2 != null) if (this.last2.next1 == null || this.last2.next2 == null) var2 = this.last2;
        Solution.Node var3 = new Solution.Node(var2, var1, (Solution.Node)null);
        if (this.last1 == null) this.last1 = var3;
        else if (this.last2 == null) this.last2 = var3;

        if(var2 == null) {
            if (this.first1==null) this.first1 = var3;
            else if (this.first2==null) this.first2=var3;
        } else {
            if (var2.next1==null) var2.next1 = var3;
            else if (var2.next2==null) var2.next2 = var3;

        }

        ++this.size;
        ++this.modCount;
    }
    private static class Node<String> {
        String item;
        Solution.Node<String> next1;
        Solution.Node<String> next2;
        Solution.Node<String> prev;

        Node(Solution.Node<String> var1, String var2, Solution.Node<String> var3) {
            this.item = var2;
            if (this.next1==null) this.next1 = var3;
            else this.next2 = var3;
            this.prev = var1;
        }
    }
}
