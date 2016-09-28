package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();
    static {
        labels.put(4.0, "sdfa");
        labels.put(64.4, "sliuew");
        labels.put(1.56, "swerw");
        labels.put(23.3, "tuiy");
        labels.put(6.876, "qertt");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
