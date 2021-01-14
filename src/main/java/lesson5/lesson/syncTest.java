package lesson5.lesson;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class syncTest {

    public static void main(String[] args) throws InterruptedException {
//        semaphoreTest(); // Выполнение пачками из трёх потоков
//        cdlTest(); // Ожидаем события.. Например - выполнение всех заданных потоков
//        cbTest();
        lockTest(); // Вариант по блокировке. Более предпочительный, чем преславутый synchronized
    }

    private static void semaphoreTest() {
        Semaphore smp = new Semaphore(3);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 15; i++) {
            executorService.execute(() -> {

                try {
                    smp.acquire(); // эстафетная палочка нашей многопоточности
                    System.out.println("Start - " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("End - " + Thread.currentThread().getName());
                    smp.release(); // передача эстафеты
                }

            });
            executorService.shutdown(); // не забываем тушить
        }
    }

    private static void cdlTest() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(7);

        for (int i = 0; i < 10; i++) {
            int k = i;

            new Thread(() -> {
                try {
                    System.out.println(k + " Start - " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(k + " End - " + Thread.currentThread().getName());
                    cdl.countDown(); // уменьшаем значение счётчика CountDownLatch(7)
                }
            }).start();
        }
        cdl.await(); // ожидаем понижение счётчика до 0 CountDownLatch(7)
        System.out.println("END!");
    }

    private static void cbTest() {
        CyclicBarrier cb = new CyclicBarrier(10);

        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Start - " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    cb.await(); // Блокируем текущий поток
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("End - " + Thread.currentThread().getName());
                }
            });
            
        }
    }

    private static void lockTest() {

        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(() -> {
            reentrantLock.lock();

                try {
                    System.out.println("Start - " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("End - " + Thread.currentThread().getName());
                }

            reentrantLock.unlock();
        }).start();

        // для 2-го потока
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("Start - " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("End - " + Thread.currentThread().getName());
            }
            reentrantLock.unlock();
        }).start();
    }
}
