package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) return;
        File result = new File(args[0]);
        Set<String> treeSet = new TreeSet<>();
        //записываем пути частей файла в сет с автоматической сортировкой
        for (int i = 1; i < args.length; i++) {
            treeSet.add(args[i]);
        }
        //создаем временный файл для склейки всех частей
        Path tmpPath = Files.createTempFile("temp",".tmp1");
        FileOutputStream fos = new FileOutputStream(tmpPath.toFile());
        //считываем все части последовательно и записываем во временный файл.
        for (String s : treeSet) {
            Files.copy(Paths.get(s), fos);
        }
        fos.close();

        //создаем поток для записи результирующего файла
        FileOutputStream resultStream = new FileOutputStream(result);
        //создаем поток для чтения архива из временного файла.
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(tmpPath.toFile()));
        ZipEntry tmpZip;

        // считываем архив в resultStream
        while ((tmpZip = zipInputStream.getNextEntry()) != null) {
            if (tmpZip.isDirectory()) continue; //если файл в директории - пропускаем директорию
            int counter = 0; //счетчик считанных байт
            byte[] buffer = new byte[1024]; //буфер для считанных байт
            while (true) {
                counter = zipInputStream.read(buffer); //считываем из архива в буфер
                if (counter<=0) break; //если достигли конца файла - прерываем чтение
                resultStream.write(buffer,0,counter); //записываем буфер в результирующий поток
            }
            zipInputStream.closeEntry();
            resultStream.close();
            break;
        }
        zipInputStream.close();
        tmpPath.toFile().delete();
    }
}
