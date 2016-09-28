package com.javarush.test.level08.lesson08.task05;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution {
    public static void main(String[] args) {
        HashMap<String, String> map1 = createMap();
        removeTheFirstNameDuplicates(map1);
        System.out.println(map1);
    }
    public static HashMap<String, String> createMap() {
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

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            String name = pair.getValue();
            int i = 0;
            for (String s : map.values()) {
                if (name.equals(s)) i++;
            }
            if (i > 1) removeItemFromMapByValue(map, name);
        }

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
