package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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
        //Set<? extends Animal> allAnimals = getAllAnimals("C:/Classes");
        Set<? extends Animal> allAnimals = getAllAnimals("C:\\Users\\MVTitov\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level35\\lesson10\\bonus01\\data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animalSet = new HashSet<>();
        String[] classFiles = new File(pathToAnimals).list();
        for (String classFile : classFiles) {
            final String finalPath = pathToAnimals+File.separator;
            ClassLoader loader = new ClassLoader()
            {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException
                {
                    try
                    {
                        byte[] temp = Files.readAllBytes(Paths.get(finalPath + name +".class"));
                        return defineClass(null,temp,0,temp.length);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                        return super.findClass(name);
                    }
                }
            };
            String className = classFile.substring(0,classFile.length()-6);
            try {
                Class clazz = loader.loadClass(className);
                for (Class aClass : clazz.getInterfaces()) {
                    if (aClass.equals(Animal.class)) {
                        animalSet.add((Animal) clazz.newInstance());
                    }
                }

            } catch (Exception e) {
            }

        }


        return animalSet;
    }


}
