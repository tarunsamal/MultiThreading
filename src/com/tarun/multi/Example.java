package com.tarun.multi;

public class Example {
    public static void main(String[] args)  {
        Thread thread = new Thread(() -> {
            //System.out.println("Hello World");
            //System.out.println( "Run :" +  Thread.currentThread().getName()+":"+Thread.currentThread().getId());
            //System.out.println("Priority :" + Thread.currentThread().getPriority());
            throw  new RuntimeException("Exception Occurred");
        });
        thread.start();
        thread.setName("Worker001");
        thread.setPriority(Thread.MAX_PRIORITY);
        //System.out.println( "Main :" + Thread.currentThread().getName()+":"+Thread.currentThread().getId());
        //Thread.sleep(10 );
        //System.out.println( "Main :" + Thread.currentThread().getName()+":"+ Thread.currentThread().getId());

        thread.setUncaughtExceptionHandler((t, e) -> System.out.println( "Thread Name is " + t.getName() + t.getId() + ":"+ e.getMessage()));
    }
}
