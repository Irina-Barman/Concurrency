package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger value = new AtomicInteger();


    public void inc() {
        value.getAndIncrement(); // получить и увеличить
    }

    public void dec() {
        value.getAndDecrement(); // получить и уменьшить
    }

    public int getValue() {
        return value.intValue();
    } // получить int значение

}
