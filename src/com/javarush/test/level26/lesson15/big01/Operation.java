package com.javarush.test.level26.lesson15.big01;

/**
 * Created by MVTitov on 12.08.2016.
 */
public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;
    public static Operation getAllowableOperationByOrdinal(Integer i) {
        if (i < 1 || i > 4) throw new IllegalArgumentException();
        return values()[i];
    }

}
