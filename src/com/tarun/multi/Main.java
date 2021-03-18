package com.tarun.multi;

import javafx.concurrent.Worker;

public class Main {
    public static void main(String[] args) {

//	Thread t1 = new Thread(new Runner1());
//
//    Thread t2 = new Thread(new Runner2());
//        System.out.println("Begin Thread 1");
//    t1.start();
//     t2.start();
//        try {
//            t2.join();
//            t1.join();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Begin Thread 2");
//
//        System.out.println("Finished Threads 1 2");
//    }
    Thread daemon = new Thread(new Deamon());
    Thread normal = new Thread(new Normal());
    daemon.setDaemon(true);
    daemon.start();
    normal.start();

    }
}
class Deamon implements Runnable
{
    @Override
    public void run() {
        while(true)
        {
            System.out.println(this.getClass().getSimpleName() +" is running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Normal implements Runnable
{
    @Override
    public void run() {

            System.out.println(this.getClass().getSimpleName() +" is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}

class Runner1 implements Runnable
{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1 :" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Runner2 implements Runnable
{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2 :" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
