package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by MVTitov on 22.08.2016.
 */
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
