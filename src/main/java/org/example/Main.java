package org.example;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue();
        new Thread(new Runnable() { // Если не вывести в отдельный поток, то не сможем добавлять новые задачи
            @Override
            public void run() {
                int counter =0; // счётчик выполнения задач
                while (true) { // в бесконечном цикле метод take возьмёт новую задачу и передаст её на выполнение
                    System.out.println("Counter: " + counter);
                    counter++;
                    Runnable task = blockingQueue.take();
                    if(task != null){
                        new Thread(task).start();
                    }
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            final int index = i;
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


