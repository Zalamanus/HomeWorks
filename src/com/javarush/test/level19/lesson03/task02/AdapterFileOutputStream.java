package com.javarush.test.level19.lesson03.task02;

/* Адаптер
Используйте класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {
    FileOutputStream os;

    public AdapterFileOutputStream(FileOutputStream os) {
        this.os = os;
    }

    @Override
    public void flush() throws IOException {
        os.flush();
    }

    @Override
    public void writeString(String s) throws IOException {
        os.write(s.getBytes());
    }

    @Override
    public void close() throws IOException {
        os.close();
    }
}

