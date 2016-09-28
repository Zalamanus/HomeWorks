package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось:
Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        Human grandad1 = new Human("Дед Леша", true, 76, null, null);
        Human grandad2 = new Human("Дед Паша", true, 67, null, null);
        Human granmom1 = new Human("Баба Таня", false, 70, null, null);
        Human granmom2 = new Human("Баба Вера", false, 71, null, null);
        Human dad = new Human("Отец Вася", true, 60, grandad1, granmom2);
        Human mom = new Human("Мама Полина", false, 57, grandad2, granmom1);
        Human child1 = new Human("Ребенок Аленко", false, 31, dad, mom);
        Human child2 = new Human("Ребенок Максимко", true, 33, dad, mom);
        Human child3 = new Human("Ребенок Нерождайко", true, 25, dad, mom);
        System.out.println(grandad1);
        System.out.println(grandad2);
        System.out.println(granmom1);
        System.out.println(granmom2);
        System.out.println(dad);
        System.out.println(mom);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);
    }

    public static class Human
    {
        public Human(String name, boolean sex, int age, Human father, Human mother)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        String name;
        boolean sex;
        int age;
        Human father,mother;

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
