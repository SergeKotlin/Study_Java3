package lesson4;

import java.util.Collections;

public class Store {
    private int product = 0;

    public synchronized void get() {
        if (product < -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product--;
        System.out.println("Гость приобрёл 1 единицу товара");
        System.out.println("Товара на складе: " + product);
        notify();
    }

    public synchronized void set() {
        if (product >= 3 ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        System.out.println("Магазин пополнил склад на 1 единицу");
        System.out.println("Товара на складе: " + product);
        notify();
    }
}

class Producer implements Runnable {
    Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            store.set();
        }
    }
}

class Consumer implements Runnable {
    Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            store.get();
        }
        
    }
}

class MainShop {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(consumer).start();
        new Thread(producer).start();
    }
}