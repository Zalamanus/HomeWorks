package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
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
        int min=Integer.MAX_VALUE;
        for (Integer integer : map.values()) {
            min = (integer < min) ? integer : min;
        }
        Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer,Integer> entry = it.next();
            if (entry.getValue()!=min) it.remove();
        }
        for (Integer integer : map.keySet()) {
            System.out.print(integer+" ");
        }
    }
}
