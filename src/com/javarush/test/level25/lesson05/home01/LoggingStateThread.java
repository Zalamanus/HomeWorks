package com.javarush.test.level25.lesson05.home01;

/**
 * Created by MVTitov on 09.08.2016.
 */
public class LoggingStateThread extends Thread {
    Thread thread;

    public LoggingStateThread(Thread target) {
        super();
        this.thread = target;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(state);
        while (state != State.TERMINATED) {
            if (state != thread.getState()) {
                state = thread.getState();
                System.out.println(state);
            }
        }

    }
}
