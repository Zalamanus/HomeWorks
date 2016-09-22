package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream is = new FileInputStream(reader.readLine());
        HashMap<Integer,Integer> map =new HashMap<>();
        while (is.available()>0) {
            int i = is.read();
            if (!map.containsKey(i)) map.put(i,1);
            else map.put(i,map.get(i)+1);
        }
        reader.close();is.close();
        int max=0;
        for (Integer integer : map.values()) {
            max = (integer > max) ? integer : max;
        }
        Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer,Integer> entry = it.next();
            if (entry.getValue()!=max) it.remove();
        }
        for (Integer integer : map.keySet()) {
            System.out.print(integer+" ");
        }
    }
}
