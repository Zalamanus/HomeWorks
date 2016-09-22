package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by MVTitov on 22.08.2016.
 */
public class MyThread extends Thread {
    private static AtomicInteger priority = new AtomicInteger(1);
    public MyThread() {
        settingPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        settingPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        settingPriority();
    }

    public MyThread(String name) {
        super(name);
        settingPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        settingPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        settingPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        settingPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        settingPriority();
    }
    private void settingPriority() {
        this.setPriority(priority.get());
        priority.incrementAndGet();
        if (priority.get()>10) priority.set(1);
    }
}
