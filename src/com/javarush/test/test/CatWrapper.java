package com.javarush.test.test;


import java.lang.reflect.Field;
import java.lang.reflect.ReflectPermission;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * Created by MVTitov on 13.07.2016.
 */
public class CatWrapper extends Cat {
    private Cat original;

    public CatWrapper(Cat cat) {

        super("asf");
        this.original = cat;
    }

    public String getName() {
        return "Кот по имени " + original.getName();
    }

    public void setName(String name) {
        original.setName(name);
    }

    public static void main(String[] args) {
        Pattern patt = Pattern.compile("<b>([^<]*)</b>");
        String htmlString = "lkj <b> wewee</b> uhb <b> xcv </b>";
        Matcher m = patt.matcher(htmlString);
        StringBuffer sb = new StringBuffer(htmlString.length());
        while (m.find()) {
            String text = m.group(1);
            m.appendReplacement(sb, Matcher.quoteReplacement(text));
        }
        m.appendTail(sb);
        System.out.println(sb.toString());

    }

    public static void printName(Cat named) {
        System.out.println(named.getName());
    }

    public static void printFibonaci(int max) {
        int first = 0, second = 0;
        for (int i = 0; i < max; i++) {
            if (i == 0) first = 1;
            else {
                first = first + second;
                second = first - second;
            }
            System.out.print(first + " ");
        }
    }
}
