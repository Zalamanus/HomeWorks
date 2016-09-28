package com.javarush.test.level17.lesson10.bonus02;

import com.javarush.test.level17.lesson10.bonus01.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople,
выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if (args.length<2) return;
        String s = arrayToString(args);
        String act = args[0];
        if (act.equals("-i")) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(s);
            while (m.find()) {
                int index = Integer.parseInt(m.group());
                printPerson(index);
            }

        }
        if (act.equals("-d")) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(s);
            while (m.find()) {
                int index = Integer.parseInt(m.group());
                deletePerson(index);
            }
        }
        if (act.equals("-c")) {
            Pattern p = Pattern.compile("(\\p{IsAlphabetic}+[\\p{IsAlphabetic}|\\s]*)\\s([мж])\\s(\\d+/\\d+/\\d+)");
            Matcher m = p.matcher(s);
            while (m.find()) {
                createPerson(m.group(1), m.group(2), m.group(3));

            }


        }
        if (act.equals("-u")) {
            Pattern p = Pattern.compile("(\\d+)\\s(\\p{IsAlphabetic}+[\\p{IsAlphabetic}|\\s]*)\\s([мж])\\s(\\d+/\\d+/\\d+)");
            Matcher m = p.matcher(s);
            while (m.find()) {
                updatePerson(m.group(1), m.group(2), m.group(3), m.group(4));
            }
        }
    }


    private synchronized static void updatePerson(String s, String s1, String s2, String s3) throws ParseException {
        Person p = allPeople.get(Integer.parseInt(s));
        String name = s1;
        String sex = s2;
        SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date birthDate = inFormat.parse(s3);
        p.setName(name);
        if (sex.equals("м")) p.setSex(Sex.MALE);
        else if (sex.equals("ж")) p.setSex(Sex.FEMALE);
        p.setBirthDay(birthDate);
    }

    private synchronized static void createPerson(String s, String s1, String s2) throws ParseException {
        String sex = s1;
        String name = s;
        SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date birthDate = inFormat.parse(s2);
        if (sex.equals("м")) allPeople.add(Person.createMale(name, birthDate));
        else if (sex.equals("ж")) allPeople.add(Person.createFemale(name, birthDate));
        System.out.println(allPeople.size() - 1);

    }

    public static void printPerson(int index) {
        if (allPeople.get(index)== null) return;
        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-YYYY", Locale.ENGLISH);
        System.out.println(allPeople.get(index).getName() + " " + (allPeople.get(index).getSex().equals(Sex.MALE) ? "м" : "ж") + " " + date.format(allPeople.get(index).getBirthDay()));

    }

    public synchronized static void deletePerson(int index) {
        allPeople.set(index,null);

    }
    public static String arrayToString(String[] arr) {
        String s="";
        for (int i = 1; i < arr.length; i++) {
            s += arr[i];
            if (i!=arr.length-1) s += " ";


        }
        return s;
    }



}
