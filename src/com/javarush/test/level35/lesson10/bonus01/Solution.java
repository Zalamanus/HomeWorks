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
        Set<? extends Animal> allAnimals = getAllAnimals("C:\\Users\\MVTitov\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animalSet = new HashSet<>();
        String rootDir;
        if (pathToAnimals.endsWith("\\") || pathToAnimals.endsWith("/"))
            rootDir = pathToAnimals;
        else rootDir = pathToAnimals + "/";

        List<String> classFiles = new ArrayList<>();
        Path pathSource = Paths.get(rootDir);
        try {
            Files.walkFileTree(pathSource, new MyFileVisitor(classFiles));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassLoader loader = new FileClassLoader(ClassLoader.getSystemClassLoader(), rootDir);


        for (String classFile : classFiles) {

            String s = Paths.get(pathToAnimals).relativize(Paths.get(classFile)).toString();
            s = s.substring(0, s.length() - 6);
            s = s.replaceAll("\\\\", ".");

            String className = s;

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

    static class FileClassLoader extends ClassLoader {
        String rootDir;

        public FileClassLoader(ClassLoader parent, String rootDir) {
            super(parent);
            this.rootDir = rootDir;
        }

        public Class findClass(String name) {
            byte[] b = loadClassData(name);
            name = name.replaceAll("\\\\", ".");
            return defineClass(name, b, 0, b.length);
        }

        private byte[] loadClassData(String name) {
            name = name.replaceAll("\\.", "\\\\");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                Files.copy(Paths.get(rootDir + name + ".class"), baos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return baos.toByteArray();
        }

    }

    static class MyFileVisitor extends SimpleFileVisitor<Path> {
        List<String> classFiles;

        public MyFileVisitor(List<String> classFiles) {
            this.classFiles = classFiles;
        }


        public FileVisitResult visitFile(Path path,
                                         BasicFileAttributes fileAttributes) {
            if (path.getFileName().toString().endsWith(".class"))
                classFiles.add(path.toString());
            return FileVisitResult.CONTINUE;
        }

    }
}
