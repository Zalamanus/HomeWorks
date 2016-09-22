package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    static List<File> filesList = new ArrayList<>();
    static String path, resultFileAbsolutePath;

    public static void main(String[] args) {
        if (args.length < 2) return;

        path = args[0];
        resultFileAbsolutePath = args[1];
        File result = new File(resultFileAbsolutePath);
        File allFilesContent = new File(result.getParent() + "/allFilesContent.txt");
        if (allFilesContent.exists()) allFilesContent.delete();

        processFiles(path);
        sortFiles(filesList);
        result.renameTo(allFilesContent);
        writeResult(filesList, allFilesContent);
        deleteEmptyDirs(path);
    }

    private static void deleteEmptyDirs(String path) {
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (file.isDirectory())
                if (!file.delete()) deleteEmptyDirs(file.getAbsolutePath());
        }
    }

    public static void processFiles(String path) {
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (file.getAbsolutePath().equals(resultFileAbsolutePath)) return;
            if (file.isDirectory()) processFiles(file.getAbsolutePath());
            else if (file.length() > 50) file.delete();
            else filesList.add(file);
        }
    }

    public static void sortFiles(List<File> listFiles) {
        Collections.sort(listFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

    }

    public static void writeResult(List<File> listFiles, File result) {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(result))) {
            String separator = "\r\n";
            for (int i = 0; i < listFiles.size(); i++) {
                if (!listFiles.get(i).exists()) continue;
                try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(listFiles.get(i)))) {
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    os.write(buffer);
                }
                if (i < listFiles.size() - 1) os.write(separator.getBytes());
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
