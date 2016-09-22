package com.javarush.test.level30.lesson04.home01;

import java.io.PrintStream;
import java.util.concurrent.TransferQueue;

/**
 * Created by MVTitov on 25.08.2016.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10; i++) {
                PrintStream ps = System.out.format("Элемент 'ShareItem-%d' добавлен",i);
                ps.println();
                queue.offer(new ShareItem("ShareItem-"+i, i));
                Thread.sleep(100);
                if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
            }

        } catch (InterruptedException e) {

        }
    }
}
