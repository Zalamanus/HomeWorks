package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.ArrayList;

/**
 * Created by MVTitov on 04.10.2016.
 */
public class FakeModel implements Model {
    private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(new ArrayList<User>());
        modelData.getUsers().add(new User("Иван", 1, 10));
        modelData.getUsers().add(new User("Вован", 2, 20));
    }
}
