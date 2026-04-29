package org.example;

import java.util.concurrent.*;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue= new LinkedBlockingQueue<>(); //интерфейс BlockingQueue, параметризирован типом Runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                int counter =0;
                while (true) {
                    System.out.println("Counter: " + counter);
                    counter++;
                    Runnable task = null;
                    try {
                        task = blockingQueue.take();
                    } catch (Exception e) {
                    }
                    new Thread(task).start();
                    /*
                    метод take никогда не вернёт null , убираем проверку
                    if(task != null){

                     */
                        new Thread(task).start();
                    }

                }
        }).start();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000); // Задержка для наглядности спящего режима.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            blockingQueue.add(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("---" + index);
                }
            });
        }


    }
}


