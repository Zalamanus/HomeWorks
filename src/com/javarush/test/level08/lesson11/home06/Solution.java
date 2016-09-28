package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Solution
{
    public static void main(String[] args)
    {
        Human child1 = new Human("Дитё1", true, 20);
        Human child2 = new Human("Дитё2", false, 18);
        Human child3 = new Human("Дитё3", true, 16);
        Human father = new Human("Отец", true, 40, Arrays.asList(child1, child2, child3));
        Human mother = new Human("Мать", false, 38, Arrays.asList(child1, child2, child3));
        Human grandad1 = new Human("Дед1", true, 70, Arrays.asList(father));
        Human grandad2 = new Human("Дед2", true, 71, Arrays.asList(mother));
        Human granba1 = new Human("Бабушка1", false, 65, Arrays.asList(father));
        Human granba2 = new Human("Бабушка2", false, 63, Arrays.asList(mother));
        System.out.println(grandad1);
        System.out.println(grandad2);
        System.out.println(granba1);
        System.out.println(granba2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);


    }

    public static class Human
    {
        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, List<Human> children) {

            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        String name;
        boolean sex;
        int age;
        List<Human> children = new ArrayList<>();

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
