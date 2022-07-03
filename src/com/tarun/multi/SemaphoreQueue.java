package com.tarun.multi;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreQueue {

    private static LinkedBlockingQueue<Long> queue = new LinkedBlockingQueue<>(10);

    private static Lock lock = new ReentrantLock();
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

            lock.lock();
            queue.offer(System.currentTimeMillis());
            lock.unlock();
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
            lock.lock();
            System.out.println(queue.poll());
            lock.unlock();
            EMPTY.release();
        }
    }

}
