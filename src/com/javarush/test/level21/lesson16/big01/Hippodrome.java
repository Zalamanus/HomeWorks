package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by MVTitov on 27.07.2016.
 */
public class Hippodrome {
    public static ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;
    public static void main(String[] args) {
        game = new Hippodrome();
        horses.add(new Horse("Резвый", 3, 0));
        horses.add(new Horse("Вялый", 3, 0));
        horses.add(new Horse("Сявка", 3, 0));
        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }
    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }
    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }
    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Horse getWinner() {
        double maxdist = 0;
        for (Horse horse : horses) {
            maxdist = horse.getDistance() > maxdist ? horse.getDistance() : maxdist;
        }
        for (Horse horse : horses) {
            if (horse.getDistance()==maxdist) return horse;
        }
        return null;
    }
    public void printWinner() {
        System.out.printf("Winner is %s!\n",getWinner().getName());
    }

}
