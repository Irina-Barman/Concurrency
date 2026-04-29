package org.example;

import java.util.LinkedList;
import java.util.Queue;


public class BlockingQueue {
    private final Queue<Runnable> queue = new LinkedList<>();
    private final Object monitor = new Object(); // наш монитор

    // добавляет элемент в очередь
    public void add(Runnable task){
        synchronized (monitor) {
            queue.add(task);
        }
    }

    // Забирает элемент из очереди
    public Runnable take() {
        synchronized (monitor) {
            return queue.poll();
        }
    }
}
