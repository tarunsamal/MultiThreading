package com.tarun.multi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hacking {
    public static void main(String[] args) {
        Vault vault = new Vault();
        List<Thread> threads = new ArrayList<>();
        threads.add(new AscHacker(vault));
        threads.add(new DescHacker(vault));
        threads.add(new Police());

        for (Thread thread:threads) {
            thread.start();
        }

    }

    public  static class Vault{
      private final int  password;
      public Vault()
      {
          this.password = new Random().nextInt(9999);
          System.out.println("Vault password" +  password);
      }
      public boolean isCorrect(int pass)
      {
          try {
              Thread.sleep(5);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          return pass == password;
      }
    }

    public static abstract class Hacker extends Thread
    {
        protected Vault vault;

        public Hacker(Vault vault)
        {
            this.setName(this.getClass().getSimpleName());
            this.vault = vault;
            this.setPriority(MAX_PRIORITY);
        }
        @Override
        public synchronized void start() {
            System.out.println(this.getClass().getSimpleName() + "is started");
            super.start();
        }

        @Override
        public void run() {
           if(hack()){
               System.out.println(this.getName() + "is guesses successfully");
           System.exit(0);
           }
        }

        public abstract boolean hack();
    }

    public static class AscHacker extends Hacker{

        public AscHacker(Vault vault) {
            super(vault);
        }

        @Override
        public boolean hack() {
            for (int i = 1 ; i< 10000 ; i++)
            {
               if(vault.isCorrect(i))
               {
                   System.out.println("Guess is " +i);
                   return true;
               }
            }
            return false;
        }



    }
    public static class DescHacker extends Hacker{

        public DescHacker(Vault vault) {
            super(vault);
        }

        @Override
        public boolean hack() {
            for (int i = 9999 ; i> 0 ; i--)
            {
                if(vault.isCorrect(i))
                {
                    System.out.println(this.getName() + "Guess is " +i);
                    return true;
                }
            }
            return false;
        }


    }
    public static class Police extends Thread
    {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.err.println("Game Over");
            System.exit(0);
        }
    }
}
