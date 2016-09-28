package com.javarush.test.level35.lesson10.bonus01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        Set<? extends Animal> allAnimals = getAllAnimals("C:\\Users\\MVTitov\\Documents\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level35\\lesson10");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<? extends Animal> animalSet = new HashSet<>();

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

        for (String path : classFiles) {
            File file = new File(path);
            try (InputStream is = new FileInputStream(file)) {
                long length = file.length();
                byte[] bytes = new byte[(int) length];
                // Read in the bytes
                int offset = 0;
                int numRead = 0;
                while (offset < bytes.length
                        && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                    offset += numRead;
                }

                // Ensure all the bytes have been read in
                if (offset < bytes.length) {
                    throw new IOException("Could not completely read file " + path);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            ClassLoader loader = ClassLoader.getSystemClassLoader();

        }

        return null;
    }

}
