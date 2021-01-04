package lesson4;

public class TestSync {
    private int id ;

    public TestSync(int id) {
        this.id = id;
    }

//    public void firstMethod() {
    public synchronized void firstMethod() {
        System.out.printf("o%d.first - start%n", id);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.printf("(o%d.first-%d)", id, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("o%d.first - end%n", id);
    }

    public void secondMethod() {
//        synchronized (this) {
        synchronized (Object.class) {
            System.out.printf("o%d.second - start%n", id);
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.printf("(o%d.second-%d)", id, i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("o%d.second - end%n", id);
        }
    }

    public static synchronized void thirdMethod() {
        System.out.printf("o.first - start%n");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.printf("(o.first-%d)", i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("o.first - end%n");
    }
}

class StartTest {
    public static void main(String[] args) {
        TestSync o1 = new TestSync(1);
        TestSync o2 = new TestSync(2);

    // Параллельное выполнение
//        new Thread(o1::firstMethod).start();
//        new Thread(o2::firstMethod).start();
    // Для последовательного выполнения добавляем оператор synchronized (в firstMethod..)
    // Блок, помеченный данным ключевым словом - будет выполняться только одним потоком одновремено.
    // Можно добавить в сам метод (в сигнатуру), можно вынести в блок (JavaRush):
    //    synchronized (this) {     // Или synchronized (MyClass.class), или вообще ...(Object.class)
    //      //...логика метода
    //    }


//        new Thread(o1::firstMethod).start();
        // Ps «::» – сокращение лямбды:
//        new Thread(()-> o2.secondMethod()).start();
        // <- разные объекты, разные методы, синх не пересекается

//        new Thread(o1::firstMethod).start();
//        new Thread(o2::firstMethod).start();
        // <- разные объекты по-прежнему, синх не пересекается

//        new Thread(o1::firstMethod).start();
//        new Thread(o1::secondMethod).start();
        // <- вот теперь синх по одному объкту, o1 (firstMethod & secondMethod помечены "sync.")

        // Меняем в secondMethod синх относительно Object.class
//        new Thread(o1::secondMethod).start();
//        new Thread(o2::secondMethod).start();
        // <- получаем синх по общему двум объектам (Object.class)

        // Посмотрим статику
//        new Thread(() -> o1.thirdMethod()).start(); // TestSync.class - и здесь используется в качестве объекта блокировки
//        new Thread(TestSync::thirdMethod).start();
        // <- синх по одному объекту TestSync.class
    }
}