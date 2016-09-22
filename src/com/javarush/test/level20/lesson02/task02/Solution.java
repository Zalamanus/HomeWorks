package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
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

            JavaRush javaRush = new JavaRush();
            User u = new User();
            u.setFirstName("Max");
            u.setLastName("Titov");
            u.setBirthDate(new Date());
            u.setMale(true);
            u.setCountry(User.Country.RUSSIA);
            javaRush.users.add(u);

            u = new User();
            u.setFirstName("Vova");
            u.setLastName("Pupkin");
            u.setBirthDate(new Date());
            u.setMale(false);
            u.setCountry(User.Country.UKRAINE);
            javaRush.users.add(u);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            for (User user : loadedObject.users) {
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getBirthDate());
                System.out.println(user.isMale());
                System.out.println(user.getCountry());
            }

            JavaRush javaRush1 = new JavaRush();
            u = new User();
            u.setFirstName("Max1");
            u.setLastName("Titov1");
            u.setBirthDate(new Date());
            u.setMale(true);
            u.setCountry(User.Country.RUSSIA);
            javaRush1.users.add(u);
            javaRush1.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject1 = new JavaRush();
            loadedObject1.load(inputStream);
            for (User user : loadedObject1.users) {
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getBirthDate());
                System.out.println(user.isMale());
                System.out.println(user.getCountry());
            }


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

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter p = new PrintWriter(outputStream);
            for (User user : users) {
                if (user.getFirstName()!=null) p.println(user.getFirstName());
                else p.println();
                if (user.getLastName()!=null) p.println(user.getLastName());
                else p.println();
                p.println(user.isMale());
                if (user.getBirthDate()!=null) p.println(user.getBirthDate().getTime());
                else p.println();
                if (user.getCountry()!=null) p.println(user.getCountry());
                else p.println();
                p.println("---");
            }
            p.println("------");
            p.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = "";
            while (reader.ready()) {
                while (!(s = reader.readLine()).equals("---")) {
                    if (s.equals("------")) break;
                    User u = new User();
                    if (!s.equals("")) u.setFirstName(s);
                    s = reader.readLine();
                    if (!s.equals("")) u.setLastName(s);
                    s = reader.readLine();
                    if (s.equals("true")) u.setMale(true);
                    else u.setMale(false);
                    s = reader.readLine();
                    if (!s.equals("")) u.setBirthDate(new Date(Long.parseLong(s)));
                    s = reader.readLine();
                    if (!s.equals("")) {
                        if (s.equals("RUSSIA")) u.setCountry(User.Country.RUSSIA);
                        if (s.equals("UKRAINE")) u.setCountry(User.Country.UKRAINE);
                        if (s.equals("OTHER")) u.setCountry(User.Country.OTHER);
                    }
                    users.add(u);
                }

            }
        }
    }
}
