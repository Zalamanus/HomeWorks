package com.javarush.test.level31.lesson06.home01;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) return;
        Map<String, ByteArrayOutputStream> map = new HashMap<>();
        String fileToArc = args[0];
        String arc = args[1];
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(arc));
        ZipEntry tmpZip;

        // считываем архив в map
        while ((tmpZip = zipInputStream.getNextEntry()) != null) {
            String fileName = tmpZip.getName();
            int counter = 0;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            do {
                counter = zipInputStream.read(buffer);
                if (counter<=0) break;
                baos.write(buffer,0,counter);
            } while (counter == buffer.length);
            map.put(fileName,baos);
            baos.close();
        }
        zipInputStream.close();

        Path newPath = Paths.get(fileToArc);
        ZipEntry newZip = new ZipEntry("new/"+newPath.getFileName().toString());
        FileInputStream fos = new FileInputStream(newPath.toFile());
        byte[] buffer = new byte[fos.available()];
        fos.read(buffer);
        fos.close();

        // создаем новый архив из map
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(arc));

        boolean finded = false;
        for (Map.Entry<String, ByteArrayOutputStream> entry : map.entrySet()) {
            if (entry.getKey().equals(newPath.getFileName().toString())) {
                finded = true;
                continue;
            }
            tmpZip = new ZipEntry(entry.getKey());
            zos.putNextEntry(tmpZip);
            if (entry.getValue()!= null) {
                zos.write(entry.getValue().toByteArray());
            }
            zos.closeEntry();
        }
        if (finded) {
            zos.putNextEntry(newZip);
            zos.write(buffer);
            zos.closeEntry();
        }

        zos.close();

    }
}
