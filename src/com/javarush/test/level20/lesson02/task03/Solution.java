package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static Properties prop = new Properties();



    public void fillInPropertiesMap() throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        InputStream is = new FileInputStream(r.readLine());
        load(is);
        is.close();

    }

    public void save(OutputStream outputStream) throws Exception {
        for (Map.Entry<String, String> stringStringEntry : properties.entrySet()) {
            prop.setProperty(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        prop.store(outputStream,"My Properties");

    }

    public void load(InputStream inputStream) throws Exception {
        prop.load(inputStream);
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            properties.put((String) entry.getKey(), (String) entry.getValue());
        }
    }
}
