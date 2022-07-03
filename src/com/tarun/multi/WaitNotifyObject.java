package com.tarun.multi;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class WaitNotifyObject {

    private static BlockingDeque<Long> queue = new LinkedBlockingDeque<>(5);

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyObject object1 = new WaitNotifyObject();
        Thread t2 = new Thread(()->object1.consume());
        Thread t1 = new Thread(()->object1.produce());
        t1.start();
        t2.start();
    }
    private synchronized void  produce(){
        while (true){
            if(queue.size() == 5){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(System.currentTimeMillis());
                System.out.println("produced " +queue.getLast());
            notify();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private synchronized void consume() {
        while (true)
        {
            if (queue.size() ==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("consumed " +queue.poll());
            notify();
        }
    }
}
