package com.javarush.test.level31.lesson10.home01;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* Читаем конфиги
Реализовать метод getProperties, который должен считывать свойства из переданного файла fileName.
fileName может иметь любое расширение - как xml, так и любое другое, или вообще не иметь.
Нужно обеспечить корректное чтение свойств.
При возникновении ошибок должен возвращаться пустой объект.
Метод main не участвует в тестировании.
Подсказка: возможно, Вам понадобится File.separator.
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        if (fileName == null) return null;
        Properties properties = new Properties();
        try {
            if (fileName.endsWith(".xml")) properties.loadFromXML(new FileInputStream(fileName));
            else properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
        }
        return properties;
    }
}
