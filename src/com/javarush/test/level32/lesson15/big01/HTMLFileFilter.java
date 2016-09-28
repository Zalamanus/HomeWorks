package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by MVTitov on 18.09.2016.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) return true;
        return file.getName().toLowerCase().endsWith(".html") || file.getName().toLowerCase().endsWith(".htm");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
