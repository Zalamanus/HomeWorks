package com.javarush.test.level19.lesson03.task02;

/* Адаптер
Используйте класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {
    private FileOutputStream os;

    public AdapterFileOutputStream(FileOutputStream os) {
        this.os = os;
    }

    @Override
    public void flush() throws IOException {
        os.flush();

    }

    @Override
    public void writeString(String s) throws IOException {
        char[] arr = s.toCharArray();
        for (char c : arr) {
            os.write((int) c);
        }
    }

    @Override
    public void close() throws IOException {
        os.close();
    }
}

