package com.tarun.multi;

public class Example2 {

    public static void main(String[] args) {
        Thread t = new Ex();
        t.start();
    }

    private static class Ex extends Thread
    {
        @Override
        public void run() {
            System.out.println("Thread is" + this.getName() + " :" +this.getId());
        }
    }
}
