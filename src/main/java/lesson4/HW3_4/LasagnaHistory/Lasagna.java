package lesson4.HW3_4.LasagnaHistory;

class Lasagna implements Runnable {

    String currentResult = "Dough Meat Cheese Dough Meat Cheese Dough Meat Cheese";
    String ingredient;

    public Lasagna(String ingredient) {
//    public Lasagna() {
        this.ingredient = ingredient;
    }

    public String getCurrentResult() {
        return currentResult;
    }


    public static void main(String[] args) {
        Lasagna meatLasagna = new Lasagna("Dough");
//        Lasagna meatLasagna = new Lasagna();

        Thread threadTheDough = new Thread(meatLasagna);
        threadTheDough.setName("put the dough");
        threadTheDough.start();

        Thread threadTheMeat = new Thread(meatLasagna);
        threadTheMeat.setName("put the meat");
        threadTheMeat.start();

        Thread threadTheCheese = new Thread(meatLasagna);
        threadTheCheese.setName("put the cheese");
        threadTheCheese.start();

    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.print(" ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//class TestMainus {
//    public static void main(String[] args) {
//        Mainus Arkasha = new Mainus();
//        Mainus Bonya = new Mainus();
//        Mainus Curt = new Mainus();
//    }
//}

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