package com.javarush.test.level27.lesson09.home01;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {

        while (isValuePresent) {
            System.out.println("Got: " + value);
            isValuePresent = false;
            notifyAll();
        }
        return value;

    }

    public synchronized void put(int value) {
        if (!isValuePresent) {
            this.value = value;
            System.out.println("Put: " + value);
            isValuePresent=true;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
