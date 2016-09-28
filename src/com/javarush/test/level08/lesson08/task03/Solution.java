package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("Пупкин", "Вова");
        map.put("Рыжков", "Алексей");
        map.put("Титов", "Максим");
        map.put("Васильев", "Василий");
        map.put("Артамонов", "Татьяна");
        map.put("Артамонова", "Елена");
        map.put("Титв", "Никита");
        map.put("Титова", "Софа");
        map.put("Артамоноа", "Тамара");
        map.put("Путин", "Вова");
        return map;

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        int i =0;
        for (String s : map.values()) {
            if (s.equals(name)) i++;
        }
        return i;

    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        int i = 0;
        for (String s : map.keySet()) {
            if (s.equals(lastName)) i++;
        }
        return i;
    }
}
