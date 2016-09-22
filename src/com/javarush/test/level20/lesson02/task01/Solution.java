package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = new File("1.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();
            System.out.println(ivanov.name);
            for (Asset asset : ivanov.assets) {
                System.out.println(asset.getName());
                System.out.println(asset.getPrice());
            }

            Human somePerson = new Human();
            somePerson.load(inputStream);
            System.out.println(somePerson.name);
            for (Asset asset : somePerson.assets) {
                System.out.println(asset.getName());
                System.out.println(asset.getPrice());
            }
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter p = new PrintWriter(outputStream);
            p.println(name);
            for (Asset asset : assets) {
                p.println(asset.getName());
                p.println(asset.getPrice());
            }
            p.println("---");
            p.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            name = reader.readLine();
            String s = "";
            while (!(s = reader.readLine()).equals("---")) {
                Asset as = new Asset(s);
                try {
                    as.setPrice(Double.parseDouble(reader.readLine()));
                } catch (NumberFormatException e) {
                    as.setPrice(0.0d);
                } catch (IOException e) {
                    throw e;
                }
                assets.add(as);
            }
        }
    }
}
