package com.javarush.test.level20.lesson10.home07;

import java.io.*;

/* Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные  - writeObject
3) сериализовать класс Solution  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution sol = new Solution("2.txt");
        sol.writeObject("rfkz,fkz");
        OutputStream os1 = new FileOutputStream("1.txt");
        ObjectOutputStream out1 = new ObjectOutputStream(os1);
        out1.writeObject(sol);

        InputStream is = new FileInputStream("1.txt");
        ObjectInputStream inp = new ObjectInputStream(is);
        Solution sol1 = (Solution) inp.readObject();
        sol1.writeObject(";iuioeklj");

    }

    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        initStream(false);
    }

    private void initStream(boolean add) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName, add);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        initStream(true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }
}
