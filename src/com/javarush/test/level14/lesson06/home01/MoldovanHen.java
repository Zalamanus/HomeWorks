package com.javarush.test.level14.lesson06.home01;

/**
 * Created by MVTitov on 01.07.2016.
 */
public class MoldovanHen extends Hen {
    String country = Country.MOLDOVA;
    @Override
    int getCountOfEggsPerMonth() {
        return 30;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - "+country+". Я несу "+getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
