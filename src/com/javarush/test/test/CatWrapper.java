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
      int a = 1;
        int b = 2;
        int c =3;
        int d =4;
        System.out.printf("%d %d %d %d\n",a,b,c,d);
        a=d=c=b;
        System.out.printf("%d %d %d %d\n",a,b,c,d);

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
