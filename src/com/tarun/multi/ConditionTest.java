package com.tarun.multi;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private static String user = null;
    private static String pass = null;

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread ui = new Thread(()->getData());
        Thread bg = new Thread(()->checkData());
        ui.start();
        bg.start();
    }



    private static void getData()
    {

        while (true)
        {
            lock.lock();
            try {
                user = String.valueOf(new Random().nextInt());
                pass = String.valueOf(new Random().nextInt());
                condition.signal();
            }
            finally {
                lock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static void checkData()
    {
        while (true) {
            if(!lock.tryLock())
                continue;
            if (null == user || null == pass) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("user is " + user + "password is" + pass);
            user = pass = null;
        }
    }
}
