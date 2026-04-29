package org.example;

import java.util.LinkedList;
import java.util.Queue;


public class BlockingQueue {
    private final Queue<Runnable> queue = new LinkedList<>();
    private final Object monitor = new Object(); // наш монитор.

    // добавляет элемент в очередь
    public void add(Runnable task){
        synchronized (monitor) {
            queue.add(task);
            monitor.notify();
        }
    }

    // Забирает элемент из очереди
    public Runnable take() {
        synchronized (monitor) {
            try {
                while (queue.isEmpty()) {// до тех пор, пока очередь пуста
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return queue.poll();
        }
    }
}
