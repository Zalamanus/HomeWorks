package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <D extends Convertable<T>,T> Map<T, D> convert(List<D> list) {
        Map<T,D> result = new HashMap();
        for (D d : list) {
            result.put(d.getKey(), d);
        }
        return result;
    }
}
