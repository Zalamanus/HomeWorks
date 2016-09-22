package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        String name;
        int height;
        int age;
        int weight;
        Human father,mother;
        Human[] children;

        public Human(String name) {
            this.name = name;
        }

        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Human(String name, int age, int weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        public Human(String name, int age, int weight, int height) {
            this.name = name;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }

        public Human(String name, Human father, Human mother) {
            this.name = name;
            this.father = father;
            this.mother = mother;
        }

        public Human(String name, int age, Human father, Human mother) {
            this.name = name;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public Human(String name, int age, Human[] children) {
            this.name = name;
            this.age = age;
            this.children = children;
        }

        public Human(String name, Human[] children) {
            this.name = name;
            this.children = children;
        }

        public Human(String name, int height, int age, int weight, Human father, Human mother) {
            this.name = name;
            this.height = height;
            this.age = age;
            this.weight = weight;
            this.father = father;
            this.mother = mother;
        }

        public Human(String name, int height, int age, int weight, Human father, Human mother, Human[] children) {
            this.name = name;
            this.height = height;
            this.age = age;
            this.weight = weight;
            this.father = father;
            this.mother = mother;
            this.children = children;
        }
    }
}
