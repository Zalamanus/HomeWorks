package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by MVTitov on 28.10.2016.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        if (age > TeenGirl.MAX_AGE) return new Woman();
        if (age > KidGirl.MAX_AGE) return new TeenGirl();
        if (age > 0) return new KidGirl();
        return null;
    }
}
