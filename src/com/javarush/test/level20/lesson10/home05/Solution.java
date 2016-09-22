package com.javarush.test.level20.lesson10.home05;

import java.io.*;
import java.util.logging.Logger;

/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(array);
            Person person = new Person("Name", "Surname", "Russia", Sex.FEMALE);
            out.writeObject(person);
            out.close();
            array.close();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(array.toByteArray()));
            Person serialPerson = (Person) in.readObject();
            System.out.println(person.greetingString + person.fullName + " " + person.country + " " + person.sex + " " + person.logger + " " + person.outputStream);
            System.out.println(serialPerson.greetingString + serialPerson.fullName + " " + serialPerson.country + " " + serialPerson.sex + " " + serialPerson.logger + " " + serialPerson.outputStream);
        } catch (NotSerializableException e) {
            e.getStackTrace();
        }
    }

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        String fullName;
        transient final String greetingString = "Hello, ";
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }
}
