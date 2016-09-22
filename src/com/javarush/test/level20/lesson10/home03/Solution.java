package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        OutputStream os = new FileOutputStream("1.txt");
        InputStream is = new FileInputStream("1.txt");
        ObjectOutputStream output = new ObjectOutputStream(os);
        ObjectInputStream input = new ObjectInputStream(is);
        Solution sol = new Solution();
        Solution.B b = sol.new B("asdf");
        System.out.println(b.name);
        output.writeObject(b);
        output.close();
        Solution.B c = (B) input.readObject();
        System.out.println(c.name);
        input.close();
        os.close();
        is.close();

    }

    public static class A {
        protected String name = "A";

        public A() {
        }

        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {

        public B(String name) {
            super(name);
            this.name += name;
        }
        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(name);
        }
        private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
            in.defaultReadObject();
            name = (String) in.readObject();
        }

    }



}
