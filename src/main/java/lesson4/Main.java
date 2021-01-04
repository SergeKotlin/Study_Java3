package lesson4;

public class Main {

    public static boolean isEnd = false;

    public static void main(String[] args) throws InterruptedException {
//        new Thread().run();
//        new Thread().start(); // Новый поток, создание

        // Через анонимный класс:
        // (самый изящный способ!)
        /* new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).run();*/

        // Сокращение через лямбду, без переопределения:
        var t1 = new Thread(() -> {
//        new Thread(() -> {
            System.out.println();
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                    System.out.print(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isEnd = true;
            }
//        }).start();
        });

        // Ещё способ создания потока
        // (для случаев частого использования однотипных потоков)
        var t2 = new Thread(new MyRunnableClass());
        // И ещё..:
        var t3 = new MyThread();

        // Приоритеты
        t1.setPriority(1);

        // Демоны
        // - службы, фоновые потоки, гасятся вместе с main, висят на заднем плане и не так жрут рес-ры
        // Можно поток превратить в демона после создания, но после запуска - будет уже поздно

        // t1.stop(); - нежелательный метод, радикально всё убивает, а у разных ОС свой регламант на обращение с потоками
        // (последствия м.б неконтролируемы, если выстраивать логику на нём  )
        // #deprecated - "осуждаемый"

        t1.start();
//        t1.join(); // - способ вклеивания в поток
        // Ещё один способ присоединения к потоку:
        // (нехороший способ, помимо блокирования main-потока, мы заставляет его ещё и исполнять проверку)
        while (true) {
            if(!t1.isAlive()) {
                break;
            }
        }
        System.out.println(isEnd);
    }
}

// Ещё один способ создания нового потока:
class MyRunnableClass implements Runnable {
    @Override
    public void run() {
        System.out.println();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                System.out.print(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.isEnd = true;
        }
    }
}

// И ещё один способ..:
class MyThread extends Thread {
    public void run() {
        System.out.println();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                System.out.print(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.isEnd = true;
        }
    }
}