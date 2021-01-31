package lesson4;

public class IntegerTest {
    private static Integer n = new Integer(1);

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (n) {
                //+ испортит последовательный запуск потоков следующая строчка:
                n++;
                System.out.println(n);
                // Очевидно, что объекты синх теперь разные
                System.out.println("A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (n) {
                n++;
                System.out.println(n);
                System.out.println("B");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (n) {
                n++;
                System.out.println(n);
                System.out.println("C");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
