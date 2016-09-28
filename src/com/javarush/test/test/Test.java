package com.javarush.test.test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MVTitov on 14.07.2016.
 */
public class Test {
    public static void main(String[] args) {
        String s = "123";
        StringBuilder sb = new StringBuilder("456");
        System.out.format("s = %s, sb = %s\n", s,sb.toString());
        swap(s,sb);
        System.out.format("s = %s, sb = %s\n", s,sb.toString());
    }

    public static void swap(String s1, StringBuilder s2) {
        s1 = "321";
        s2.delete(0,3);
        s2.append("654");
        System.out.format("s = %s, sb = %s\n", s1,s2.toString());
    }
}
