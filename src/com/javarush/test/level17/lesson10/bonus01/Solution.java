package com.javarush.test.level17.lesson10.bonus01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 6 || args.length == 3)
            return;
        if (args[0].equals("-i")) {
            int index = Integer.parseInt(args[1]);
            SimpleDateFormat date = new SimpleDateFormat("dd-MMM-YYYY", Locale.ENGLISH);
            String sex = "";
            System.out.println(allPeople.get(index).getName() + " " + (allPeople.get(index).getSex().equals(Sex.MALE) ? "м" : "ж") + " " + date.format(allPeople.get(index).getBirthDay()));
        }
        if (args[0].equals("-d")) {
            int index = Integer.parseInt(args[1]);
            allPeople.set(index, null);
        }
        if (args[0].equals("-c")) {
            String[] bd=new String[3];
            String sex="";
            String name="";
            if (args.length==4) {
                bd = args[3].split("/");
                sex = args[2];
                name = args[1];
            }
            if (args.length==5) {
                bd = args[4].split("/");
                sex = args[3];
                name = args[1] + " " + args[2];
            }
            Date birthDate = new Date(Integer.parseInt(bd[2])-1900, Integer.parseInt(bd[1])-1, Integer.parseInt(bd[0]));
            if (sex.equals("м")) {
                allPeople.add(Person.createMale(name, birthDate));
            } else if (sex.equals("ж")) {
                allPeople.add(Person.createFemale(name, birthDate));

            }
            System.out.println(allPeople.size()-1);



        }
        if (args[0].equals("-u")) {
            String[] bd = new String[3];
            Person p = allPeople.get(Integer.parseInt(args[1]));
            String name ="";
            String sex = "";
            if (args.length==5) {
                bd = args[4].split("/");
                name = args[2];
                sex = args[3];
            }
            if (args.length==6) {
                bd = args[5].split("/");
                name = args[2] + " " + args[3];
                sex = args[4];
            }
            Date birthDate = new Date(Integer.parseInt(bd[2])-1900, Integer.parseInt(bd[1])-1, Integer.parseInt(bd[0]));
            p.setName(name);
            if (sex.equals("м")) p.setSex(Sex.MALE);
            else if (sex.equals("ж")) p.setSex(Sex.FEMALE);
            p.setBirthDay(birthDate);


        }


    }
}
