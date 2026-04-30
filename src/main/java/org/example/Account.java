package org.example;

public class Account {
    private int amount1;
    private int amount2;
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    public Account(int amount1, int amount2) {
        this.amount1 = amount1;
        this.amount2 = amount2;
    }

    public void transferFrom1To2(int amount) { // amoun сумма денег которую хотим перевести
        synchronized (monitor1) {
            try {
                Thread.sleep(2000); // имитация
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(amount <= amount1) { // проверяем достаточно ли денег
                System.out.println("amount <= amount1");
                synchronized (monitor2) { // работаем со вторым счётом
                    try {
                        Thread.sleep(2000); // имитация
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    amount1 -= amount; // вычитаем из со счета 1
                    amount2 += amount; // прибавляем к счёту 2
                }

            } else {
                System.out.println("Insufficient funds");
            }
        }
    }

    public void transferFrom2To1(int amount) { // amoun сумма денег которую хотим перевести
        synchronized (monitor2) {
            try {
                Thread.sleep(2000); // имитация
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(amount <= amount2) { // проверяем достаточно ли денег
                System.out.println("amount <= amount2");
                synchronized (monitor1) { // работаем со вторым счётом
                    try {
                        Thread.sleep(2000); // имитация
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    amount2 -= amount; // вычитаем из со счета 2
                    amount1 += amount; // прибавляем к счёту 1
                }

            } else {
                System.out.println("Insufficient funds");
            }
        }
    }
}
