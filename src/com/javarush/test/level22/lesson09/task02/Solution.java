package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {


    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder s = new StringBuilder();
        if (params == null || params.size()==0) return s;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue()!=null && entry.getKey()!=null)
            s.append(String.format("%s = \'%s\' and ", entry.getKey(), entry.getValue()));
        }
        s.setLength(s.length()-5);
        return s;
    }
}
