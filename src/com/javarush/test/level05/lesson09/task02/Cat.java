package com.javarush.test.level05.lesson09.task02;

/* Создать класс Cat
Создать класс Cat (кот) с пятью конструкторами:
- Имя,
- Имя, вес, возраст
- Имя, возраст (вес стандартный)
- вес, цвет, (имя, адрес и возраст – неизвестные. Кот - бездомный)
- вес, цвет, адрес ( чужой домашний кот)
Задача конструктора – сделать объект валидным. 
Например, если вес не известен, то нужно указать какой-нибудь средний вес. 
Кот не может ничего не весить. То же касательно возраста. А вот имени может и не быть (null). То же касается адреса: null.
*/

public class Cat
{
    String name,color,adress;
    int weight,age;
    public Cat(String name) {
        this.name = name;
        weight = 10;
        age = 5;
        color = "black";
    }
    public Cat(String name, int weight, int age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        color = "black";
    }
    public Cat(String name, int age) {
        this.name = name;
        this.weight = 10;
        this.age = age;
        color = "black";
    }
    public Cat(int weight, String color) {
        this.weight = weight;
        age = 5;
        this.color = color;
    }
    public Cat(int weight, String color, String adress) {
        this.weight = weight;
        age = 5;
        this.color = color;
        this.adress = adress;
    }
}
