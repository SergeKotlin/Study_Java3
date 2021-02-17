package lesson5.lesson;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

//        testFixExecutor();

//        testScheduleExecutor();

        testCollections();

    }

    private static void testFixExecutor() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3, r -> {
            System.out.println("New Thread!");
            return new Thread(r);
        });
        // Сделали на фабрики три рабочих (процесса). Теперь работу они делят сами, а мы не паримсчя B-)
        for (int i = 0; i < 10; i++) {
            final int k = i;
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - " + k));
        }

        pool.shutdown(); // приостановка нашей Фабрики потоков
        pool.shutdown(); // полностью завершение работы нашей Фабрики потоков
        pool.awaitTermination(1000, TimeUnit.MILLISECONDS); // полное завершение по таймеру (работы нашей Фабрики потоков)
    }

    private static void testScheduleExecutor() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.scheduleAtFixedRate(() -> {
            System.out.println(new Date() + " start");
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() + " end");
        }, 0, 5000, TimeUnit.MILLISECONDS);
        // <-- period типа лонг, т.е м.б написано 5000L
    }

    private static void testCollections() throws InterruptedException {
        Map<String, String> tm = Collections.synchronizedMap(new TreeMap<>());

        Map<String, String> chm = new ConcurrentHashMap<>();

        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(2);
        abq.add("a");
        abq.add("b");
        abq.put("d"); // put - вежливо добавление, как только освободится место
        abq.offer("d"); // попытаемся один раз добавить, и оставим в случае неудачи
    }
}
