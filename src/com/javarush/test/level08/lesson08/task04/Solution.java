package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String, Date> map1 = createMap();
        System.out.println(map1);
        removeAllSummerPeople(map1);
        System.out.println();
        System.out.println(map1);
    }
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Stfallone", new Date("JUNE 2 1980"));
        map.put("S", new Date("OCT 1 1980"));
        map.put("St", new Date("JULY 1 1980"));
        map.put("Sta", new Date("JUNE 1 1980"));
        map.put("Stal", new Date("JUNE 1 1980"));
        map.put("Staldfs", new Date("JUNE 1 1980"));
        map.put("Stall", new Date("JUNE 1 1980"));
        map.put("Stallo", new Date("MAY 1 1980"));
        map.put("Stallon", new Date("JUNE 1 1980"));

        return map;

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Date> pair = iterator.next();
            Date date = pair.getValue();
            if (date.getMonth()>4 && date.getMonth()<8) iterator.remove();
        }

    }
}
