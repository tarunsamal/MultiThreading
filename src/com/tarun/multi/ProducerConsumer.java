package com.tarun.multi;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    private  Object lock = new Object();
    List<Integer> list = new ArrayList<>();
    private static int UPPERLIMIT=5;
    private static int LOWERLIMIT=0;
    private int value=0;

    private void produce() throws InterruptedException {
        synchronized (lock) {
        while(true) {

                if (list.size() == UPPERLIMIT) {
                    System.out.println("Waiting to Consume");
                    lock.wait();
                } else {
                    System.out.println("Produced " + value);
                    list.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (lock) {
        while(true) {

                if (list.size() == LOWERLIMIT) {
                    System.out.println("Waiting to Produce");
                    value = 0;
                    lock.wait();

                } else {

                    System.out.println("Consumed " + list.remove(list.size()-1));
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer app = new ProducerConsumer();
        Thread produce = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consume = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        produce.start();
        consume.start();
    }

}
