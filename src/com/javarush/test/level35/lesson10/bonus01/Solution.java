package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals("C:\\Classes");
        System.out.println(allAnimals);
    }

    public static <T extends Animal> Set<T> getAllAnimals(String pathToAnimals) {
        Set<T> animalSet = new HashSet<>();

        String rootDir = pathToAnimals;

        File root = new File(rootDir);
        File[] files = root.listFiles();
        List<String> classFiles = new ArrayList<>();
        int i = 0;

        while (i < files.length) {
            File firstElement = files[i];
            File[] subFiles = null;

            if (firstElement.isDirectory()) {
                subFiles = firstElement.listFiles();
            } else {
                i++;
                continue;
            }
            File[] temp = new File[files.length + subFiles.length];
            for (int j = 0; j <= i; j++)
                temp[j] = files[j];
            for (int k = 0; k < subFiles.length; k++)
                temp[i + 1 + k] = subFiles[k];
            for (int m = i + 1; m < files.length; m++)
                temp[m + subFiles.length] = files[m];

            files = temp;
            i++;
        }

        for (File file : files) {
            if (!file.isDirectory() && file.getName().endsWith(".class")) classFiles.add(file.getAbsolutePath());
        }

        ClassLoader loader = ClassLoader.getSystemClassLoader();


        for (String classFile : classFiles) {

            String s = Paths.get(pathToAnimals).relativize(Paths.get(classFile)).toString();
            s = s.substring(0,s.length()-6);
            s = s.replaceAll("\\\\",".");

            String className = s;

            try {
                Class clazz = loader.loadClass(className);
                for (Class aClass : clazz.getInterfaces()) {
                    if (aClass.getSimpleName().equals("Animal")) {
                        animalSet.add((T) clazz.newInstance());
                    }
                }
            } catch (Exception e) {
            }
        }




        return animalSet;
    }

/*
    public static class ModuleLoader extends ClassLoader {
*/
/*
        public ModuleLoader(String pathtobin, ClassLoader parent) {
            super(parent);
        }
*/



}
