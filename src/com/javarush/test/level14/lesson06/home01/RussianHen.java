package com.javarush.test.level14.lesson06.home01;

/**
 * Created by MVTitov on 01.07.2016.
 */
public class RussianHen extends Hen {
    String country = Country.RUSSIA;
    @Override
    int getCountOfEggsPerMonth() {
        return 10;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - "+country+". Я несу "+getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
