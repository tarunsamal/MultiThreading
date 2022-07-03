package com.tarun.multi;

import java.math.BigInteger;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
       /* System.out.println("Hello World");
        Thread thread = new Thread(new TestInterrupts());
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();*/

        Thread thread = new Thread(new ComputePower(new BigInteger("2"),new BigInteger("100000000")));
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

    }

    private static class ComputePower implements Runnable
    {
       private BigInteger base ;
        private BigInteger power;

        public ComputePower(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println("ANS is " + compute());
        }

        public  BigInteger compute()
        {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO ; i.compareTo(power) !=0  ; i=i.add(BigInteger.ONE)) {
                if(Thread.interrupted())
                {
                    System.out.println("Thread Interrupted");
                    return result;
                }
                    result = result.multiply(base);
                System.out.println(base+"^"+ i+"="+result);
            }
            return result;
        }
    }
    private static class TestInterrupts implements Runnable
    {

        @Override
        public void run() {
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                System.err.println("Thread is interrupted");
            }
        }
    }
}
