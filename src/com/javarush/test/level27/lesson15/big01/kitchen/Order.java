package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MVTitov on 16.08.2016.
 */
public class Order {
    protected List<Dish> dishes = new ArrayList<>();
    private Tablet tablet;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }
    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if (dishes.size() == 0) return "";
        else {
            StringBuilder s = new StringBuilder("[");
            for (int i = 0; i < dishes.size(); i++) {
                s.append(dishes.get(i));
                if (i!=dishes.size()-1) s.append(", ");

            }
            s.append("]");
            return String.format("Your order: %s of %s", s, tablet);
        }
    }
    public int getTotalCookingTime() {
        int totalTime = 0;
        for (Dish dish : dishes) {
            totalTime+=dish.getDuration();
        }
        return totalTime;
    }
    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }
}
