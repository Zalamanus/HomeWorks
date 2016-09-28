package com.javarush.test.level20.lesson02.task04;

import java.io.*;

/* Читаем и пишем в файл статики
Реализуйте логику записи в файл и чтения из файла для класса ClassWithStatic
Метод load должен инициализировать объект включая статические поля данными из файла
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = new File("1");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            ClassWithStatic classWithStatic = new ClassWithStatic();
            classWithStatic.i = 3;
            classWithStatic.j = 4;
            classWithStatic.save(outputStream);
            outputStream.flush();

            ClassWithStatic loadedObject = new ClassWithStatic();
            loadedObject.staticString = "something";
            loadedObject.i = 6;
            loadedObject.j = 7;

            loadedObject.load(inputStream);
            System.out.println(classWithStatic.staticString);
            System.out.println(classWithStatic.i);
            System.out.println(classWithStatic.j);

            System.out.println(loadedObject.staticString);
            System.out.println(loadedObject.i);
            System.out.println(loadedObject.j);

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class ClassWithStatic {
        public static String staticString = "it's test static string";
        public int i;
        public int j;

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter p = new PrintWriter(outputStream);
            if (staticString!=null) p.println(staticString);
            else p.println();
            p.println(i);
            p.println(j);
            p.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            String s = r.readLine();
            if (!s.equals("")) staticString = s;
            i = Integer.parseInt(r.readLine());
            j = Integer.parseInt(r.readLine());
            r.close();
        }
    }
}
