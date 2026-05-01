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
        ExecutorService executorService = Executors.newFixedThreadPool(3); // создадим пул на 3 потока
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
                    workWithFileSystem();
                    System.out.println(name + " - finished working.");
                }

            });

        }
        executorService.shutdown();
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
