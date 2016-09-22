package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable,CustomThreadManipulator {
    Thread thread;

    @Override
    public void run() {
        while (!thread.isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void stop() {
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(String threadName) {
        thread = new Thread(this,threadName);
        thread.start();
    }
}
