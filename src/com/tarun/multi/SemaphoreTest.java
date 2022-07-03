package com.tarun.multi;

import sun.font.LayoutPathImpl;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static volatile long time;

    private static Semaphore FULL = new Semaphore(0);
    private static  Semaphore EMPTY = new Semaphore(1);


    public static void main(String[] args) {
        Thread produce = new Thread(()->producer());
        Thread consume = new Thread(()->consumer());
        produce.start();
        consume.start();
    }

    public static void producer(){
        while (true) {
            try {
                EMPTY.acquire();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            time = System.currentTimeMillis();
            FULL.release();
        }
    }

    public static void consumer(){
        while(true) {
            try {
                FULL.acquire();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(time);
            EMPTY.release();
        }
    }

}
