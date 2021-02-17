package lesson5.lesson;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    // дословно - Пример Реентерабельной Блокировки Чтения Записи
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static String message = "a";

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Read());
        Thread t2 = new Thread(new WriteA());
        Thread t3 = new Thread(new WriteB());
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    private static class Read implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                // Проверка, закрыт ли объект (для текущей записи)
                if (lock.isWriteLocked()) {
                    System.out.println("I'll take the lock from Write");
                }
                lock.readLock().lock(); // записывать может один, а вот "чтецов" - сколько угодно
                // Т.о. "чтецы" работают в перерывы между "писцами"
                System.out.println("ReadThread " + Thread.currentThread().getId() + "---> Message is " + message);
                lock.readLock().unlock();
            }
        }
    }

    private static class WriteA implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.writeLock().lock();
                    // "писцы" не спрашивают разрешения.. Приходят, захватывают объект для записи, что-то записывают - и освобождают объект
                    message = message.concat("a");
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }

    private static class WriteB implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.writeLock().lock();
                    // второй поток ждёт поток (блок) первого "писца", соответственно
                    message = message.concat("b");
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }
}
