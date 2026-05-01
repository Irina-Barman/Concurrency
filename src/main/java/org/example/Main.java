package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        /*
        Создаем 10 потоков, которые выполняют своих задачи,
        а потом начинают работу с файловой системой.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(3); // кол-во потоков, которые допускаем к ресурсу

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() { // передаём задачи в пулл
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " - started working.");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        semaphore.acquire(); // уменьшит счётчик потоков на 1
                        workWithFileSystem(); // запустит работу с файловой системой
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally { // чтобы блок выполнился в любом случае
                        semaphore.release(); // увеличит счётчик
                    }
                    System.out.println(name + " - finished working.");
                }

            });

        }
        executorService.shutdown(); // остановили ожидание задач
    }

    private static void workWithFileSystem(){
        /*
        Имитируем работу файловой системы, метод выводит
        имя потока который начал работу с файловой системой,
        устанавливает задержку и выводит имя потока закончившего работу с файловой системой.
         */
        String name = Thread.currentThread().getName();
        System.out.println(name + " - started working with file system.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " - finished working with file system.");

    }
}
