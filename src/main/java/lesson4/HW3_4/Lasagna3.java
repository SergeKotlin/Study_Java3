package lesson4.HW3_4;

class Lasagna3 {

    String currentResult = "Dough Meat Cheese Dough Meat Cheese Dough Meat Cheese";
    static final Lasagna3 meatLasagna = new Lasagna3("Meat");
    String mainIngredient;
    String name;
    int indexOfLayers = 3;
    int doughStep = 0;
    int meatStep = 0;
    int cheeseStep = 0;

    public Lasagna3(String mainIngredient) {
        this.mainIngredient = mainIngredient;
        this.name = mainIngredient + " lasagna";
    }

    public String getCurrentResult() {
        return currentResult;
    }

    public String getName() {
        return name;
    }


    public static void main(String[] args) {

        startThread();

        // ВТОРОЙ (мясной) ПОТОК
        startLayerThread("threadPutTheMeat", "\u001B[31m", "Meat", 2);

        // ТРЕТИЙ (сырный) ПОТОК
        startLayerThread("threadPutTheCheese", "\u001B[33m", "Cheese", 3);

    } // main end

    private static void startLayerThread(String threadName, String color, String ingredient, int i) {
        var threadPutTheIngredient = new Thread(() -> {
            Thread.currentThread().setName(threadName);
            String nameOfCurrentThread = Thread.currentThread().getName();
            System.out.println("Поток " + nameOfCurrentThread + " начал действие");
            try {
                Thread.sleep(100);
                meatLasagna.putLayer(color + ingredient + "\u001B[0m", i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPutTheIngredient.start();
    }

    private static void startThread() {
        startLayerThread("threadPutTheDough", "\u001B[37m", "Dough", 1);
    }


    public void putLayer(String theLayer, int indexOfLayer) {
        synchronized (meatLasagna) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                    // Отладка
//                    System.out.print("Шлёпаем .. " + theLayer + ", " + (i+1) + "-layer");
//                    System.out.println();
                    //
//                    System.out.printf("%s ", theLayer);
                    switch (indexOfLayer) {
                        case (1) :
                            while ( !(doughStep == meatStep && doughStep == cheeseStep) ) {
                                wait();
                            }
                            doughStep++;
                            System.out.printf("%s ", theLayer);
                            notifyAll();
                            break;
                        case (2) :
                            while ( !(meatStep < doughStep) ) {
                                wait();
                            }
                            meatStep++;
                            System.out.printf("%s ", theLayer);
                            notifyAll();
                            break;
                        case (3) :
                            while ( !(cheeseStep < meatStep) ) {
                                wait();
                            }
                            cheeseStep++;
                            System.out.printf("%s ", theLayer);
                            notifyAll();
                            finishMessage(i);
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void finishMessage(int i) {
        if (i == 4) {
            System.out.println();
            System.out.println(meatLasagna.getName() + " собрана!");
        }
    }

} // End Class

/** ✓1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз
 * (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 *
 2. Написать небольшой метод, в котором 3 потока построчно пишут данные в файл
 * (по 10 записей с периодом в 20 мс).
 *
 3. Написать класс МФУ, на котором возможно одновременно выполнять печать и сканирование документов,
 * но нельзя одновременно печатать или сканировать два документа.
 * При печати в консоль выводится сообщения «Отпечатано 1, 2, 3,... страницы»,
 * при сканировании – аналогично «Отсканировано...».
 * Вывод в консоль с периодом в 50 мс.
 * **/
//Serega, sure